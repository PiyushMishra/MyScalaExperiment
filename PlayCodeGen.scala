package org.vz.datasci

import scala.collection.mutable.ListBuffer

trait PlayCodeGeneration extends CodeGenerationTrait {
  val universe: scala.reflect.runtime.universe.type = scala.reflect.runtime.universe
  import universe._
  def generate(models: ListBuffer[Bean]) = {

    val caseClassesInfo = models.map {
      bean =>
        val params = bean.fields.map { field =>
          val fieldName = TermName(field.name)
          val fieldType = TypeName(field.dataType)
          q"$fieldName:$fieldType"
        }

        val paramsName = bean.fields.map { field =>
          val fieldName = TermName(field.name)

          q"$fieldName"
        }.toSet

        val names = bean.fields.map(_.name)

        val datatypes = bean.fields.map(_.dataType)

        val arity = bean.fields.size

        val caseClassname = TypeName(bean.name)

        val caseClassTermName = TermName(bean.name)

        val caseclassWrites = TermName(bean.name.toLowerCase() + "Writes")

        val codeTermName = TermName("jsonFormat" + arity)

        (names, arity, caseClassname, caseClassTermName, caseclassWrites, codeTermName, params, datatypes)
    }

    val caseClasses = (caseClassesInfo.map { x =>
      val (caseClassname, params) = (x._3, x._7)
      showCode(q"""case class $caseClassname(..$params)""")
    }) mkString ("\n  ")

    val reads = s"""package org.vz.datasci
      
import scala.collection.mutable.ListBuffer
import play.api.libs.json._
import play.api.libs.functional.syntax._
                  
object PlayJsonImplicits {
  ${"\n  " + caseClasses + "\n"}
  ${
      caseClassesInfo map {

        case (names, arity, caseClassName, caseClassTermName, caseclassWrites, codeTermName, params, datatypes) =>

          val s = (names zip datatypes).map { case (x, y) => q"$x" -> q"${TermName(caseClassTermName.toString().toLowerCase + "." + x)}" }

          if (arity == 1)

            showCode(q"""implicit object $caseClassTermName extends Writes[$caseClassName]{
            def writes(${TermName(caseClassTermName.toString.toLowerCase)}:$caseClassName) = Json.obj(..$s)
            implicit val reads:Reads[$caseClassName] = (__ \ ${names.head}).read[${TypeName(datatypes.head)}].map($caseClassTermName.apply)
          }""") + "\n"

          else {

            val tuples = (names zip datatypes) map {
              case (name, dtype) =>
                if (dtype.startsWith("Option")) {
                  val mdType = dtype.substring(dtype.indexOf("[") + 1, dtype.indexOf("]"))
                  q"""(__ \ $name).readNullable[${TypeName(mdType)}]"""
                } else
                  q"""(__ \ $name).read[${TypeName(dtype)}]"""
            }

            val m = (s"""
             implicit def reads: Reads[$caseClassName] = ${
              "(\n" + (tuples map (a => (showCode(a)))).mkString(" and \n") + ")"
            }($caseClassTermName.apply _)""")

            val tuples2 = (names zip datatypes) map {
              case (name, dtype) =>
                if (dtype.startsWith("Option")) {
                  val mdType = dtype.substring(dtype.indexOf("[") + 1, dtype.indexOf("]"))
                  q"""(__ \ $name).writeNullable[${TypeName(mdType)}]"""
                } else
                  q"""(__ \ $name).write[${TypeName(dtype)}]"""
            }

            val m2 = (s"""
            implicit def Writes: Writes[$caseClassName] = ${
              "(\n" + (tuples2 map (a => (showCode(a)))).mkString(" and \n") + ")"
            }(unlift($caseClassTermName.unapply _))""")
            showCode(q"""implicit object $caseClassTermName{
                       ${TermName(m2)}
                       ${TermName(m)}
            }
            """) + "\n"

          }

      } mkString ("\n")
    }
    }
   """

    reads.replaceAll("`", "")
  }

}

