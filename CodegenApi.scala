package org.vz.datasci

import scala.collection.mutable.ListBuffer
import scalariform.formatter.preferences._
import scalariform.formatter.ScalaFormatter
import scalariform.parser.ScalaParserException
import scala.annotation.tailrec

trait CodeGenerationTrait {
  def generate(models: ListBuffer[Bean]): String
}

abstract class CodeGeneration {

  self: CodeGenerationTrait =>

  import Validate._

  def write(models: Set[Bean], path: String) = {
    val modelsAfterResolvedDependency = Graph.resolveDependency(Graph.build(Graph.findDependencyArrows(models.map(beanValidation(_)))).toMap)
    val code = generate(modelsAfterResolvedDependency)
    val preferences = FormattingPreferences().setPreference(IndentSpaces, 2).setPreference(AlignParameters, false).
      setPreference(SpacesWithinPatternBinders, true).setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 40).
      setPreference(DoubleIndentClassDeclaration, true).setPreference(IndentPackageBlocks, true).setPreference(DanglingCloseParenthesis, Preserve)

    val formattedCode = try {
      ScalaFormatter.format(code, preferences)
    } catch {
      case e: ScalaParserException => "Syntax error in Scala source"
    }

    OfflineCodeGen.saveToFile(path, formattedCode.replaceAll("\r", ""))
  }
}

object OfflineCodeGen {
  import java.io.File
  def saveToFile(path: String, code: String) = {
    new File(path.substring(0, path.lastIndexOf("\\"))).mkdirs()
    val writer = new java.io.PrintWriter(path, "UTF-8")
    try writer.write(code)
    finally writer.close()
  }
}

case class Bean(name: String) {
  val depends: ListBuffer[String] = ListBuffer()
  val fields = ListBuffer[Field]()
  def ~(nameAndType: String) = {
    val arr = nameAndType.split(":")
    fields.+=(Field(arr(0).trim, arr(1).trim))
    this
  }
  def ^(name: String*) = { this.depends.++=(name); this }
}

case class Field(name: String, dataType: String) {
  import scala.reflect.runtime.universe._
  import scala.tools.reflect.ToolBox
  val tb = runtimeMirror(getClass.getClassLoader).mkToolBox()

  def analysis(beanName: String) = {

    val types = tb.parse(dataType).children
    
    val tokens = allTokens(types)

    val isRecursive = tokens.exists { _.toString.trim.equalsIgnoreCase(beanName)}

    TypesInfo(tokens(0).toString, dataType, isRecursive)
    
  }

  def allTokens(children: List[Tree]): List[Tree] = {
    @tailrec
    def allTokensHelp(children: List[Tree], acc: List[Tree]): List[Tree] = children match {
      case Nil =>
        println(acc)
        acc
      case x::xs if(xs.map(_.children).flatten == List())  => 
         println(s"x:$x xs:$xs acc:$acc")
        acc.:+(x) ++ xs
      case x :: xs =>
        println(s"donex:$x xs:$xs acc:$acc")
        allTokensHelp(xs.map{x => if(x.children.isEmpty) List(x) else x.children}.flatten,  acc.:+(x))
    }

    allTokensHelp(children, tb.parse("").children)
  }

}
case class TypesInfo(parentType: String, argType: String, isRecusrive: Boolean)

object FieldTest extends App {
  import scala.reflect.runtime.universe._
  import scala.tools.reflect.ToolBox
  val tb = runtimeMirror(getClass.getClassLoader).mkToolBox()
  val s = Field("person", "Map[String, Person]")

  println(s.allTokens(tb.parse("Map[String, Person]").children))

}


