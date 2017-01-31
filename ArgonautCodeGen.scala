package org.vz.datasci

import scala.collection.mutable.ListBuffer

trait ArgonautCodeGeneration extends CodeGenerationTrait {
  val universe: scala.reflect.runtime.universe.type = scala.reflect.runtime.universe
  import universe._

  val s = q"""
      package org.vz.datasci.json {
      import _root_.argonaut._
      import argonaut.Argonaut._
      abstract class FromJson[T: CodecJson] {
       def fromJson(json: Json) = json.spaces4.decodeOption[T]
       def fromJson(jsonString: String) = jsonString.decodeOption[T]
       def toJson(a: T) = implicitly[CodecJson[T]].encode(a)
      }
      
      object JsonImplicits {
      println("Case classes and code")
      }}"""

  override def generate(models: ListBuffer[Bean]): String =
    {
      val universe: scala.reflect.runtime.universe.type = scala.reflect.runtime.universe
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

        val arity = bean.fields.size

        val caseClassname = TypeName(bean.name)

        val caseClassTermName = TermName(bean.name)

        val caseclassCodeName = TermName(bean.name.toLowerCase() + "Codec")

        val codeTermName = TermName("casecodec" + arity)

        (names, arity, caseClassname, caseClassTermName, caseclassCodeName, codeTermName, params)
      }

      val caseClasses = caseClassesInfo.map { x =>
        val (caseClassname, params) = (x._3, x._7)
        "    " + showCode(q"""case class $caseClassname(..$params)""") + "\n"
      } mkString ("")

      val caseClassesObjects = caseClassesInfo.map { x =>
        val (caseClassTermName, caseClassname) = (x._4, x._3)
        "    " + showCode(q"""object $caseClassTermName extends FromJson[$caseClassname]""") + "\n"
      } mkString ("")

      val caseClasseImplicits = caseClassesInfo.map { x =>
        val (caseclassCodeName, caseClassname, codeTermName, caseClassTermName, names) = (x._5, x._3, x._6, x._4, x._1)
        "    " + showCode(q"""
      implicit val ${caseclassCodeName}: CodecJson[$caseClassname] = $codeTermName($caseClassTermName.apply, $caseClassTermName.unapply)(..$names)
      """) + "\n"
      } mkString ("")

      val code = caseClasses + "\n" + caseClassesObjects + "\n" + caseClasseImplicits

      ((showCode(s).split("\n").dropRight(2).toList.mkString("\n") + "\n" + code.replaceAll("`", ""))) +
        """  }
}"""

    }

}