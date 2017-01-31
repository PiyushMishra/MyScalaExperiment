package org.vz.datasci

import scala.collection.mutable.ListBuffer

trait SparyCodeGen extends CodeGenerationTrait {
  val universe: scala.reflect.runtime.universe.type = scala.reflect.runtime.universe
  import universe._

  def generate(models: ListBuffer[Bean]): String = {

    val caseClassesInfo = models.map { bean =>

      val params = bean.fields.map { field =>
        val fieldName = TermName(field.name)
        val fieldType = TypeName(field.dataType)
        q"$fieldName:$fieldType"
      }

      val paramsName = bean.fields.map { field =>
        val fieldName = TermName(field.name)
        q"$fieldName"
      }

      val names = bean.fields.map(_.name)

      val datatypes = bean.fields.map(_.dataType)

      val arity = bean.fields.size

      val caseClassname = TypeName(bean.name)

      val caseClassTermName = TermName(bean.name)

      val caseclassCodeName = TermName(bean.name.toLowerCase() + "Format")

      val codeTermName = TermName("jsonFormat" + arity)

      (names, arity, caseClassname, caseClassTermName, caseclassCodeName, codeTermName, params, datatypes)
    }

    val caseClasses = (caseClassesInfo.map { x =>
      val (caseClassname, params) = (x._3, x._7)
      showCode(q"""case class $caseClassname(..$params)""") + "\n"
    }) mkString ("")

    val customJsonProtocol = "object CustomJsonProtocol extends DefaultJsonProtocol {" + "\n" + (caseClassesInfo.map { x =>

      val (caseclassCodeName, caseClassname, codeTermName, caseClassTermName, names, datatypes) = (x._5, x._3, x._6, x._4, x._1, x._8)

      if (datatypes.exists(_.contains(caseClassname.toString())))

        "  " + showCode(q"""implicit val ${caseclassCodeName}:RootJsonFormat[$caseClassname]  = rootFormat(lazyFormat($codeTermName($caseClassTermName)))""") + "\n"

      else "  " + showCode(q"""implicit val ${caseclassCodeName}:RootJsonFormat[$caseClassname]  = $codeTermName($caseClassTermName)""") + "\n"

    }).mkString("") + "}"

    val code = (caseClasses + "\n" + customJsonProtocol).replaceAll("`", "")

    """package com.vz.datasci
import spray.json._
import spray.json.DefaultJsonProtocol""" + "\n" + "\n" + code
  }

}