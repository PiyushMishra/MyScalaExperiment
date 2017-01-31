package com.play.json

import org.vz.datasci.Bean
import org.vz.datasci.CodeGenerationTrait
import scala.collection.mutable.ListBuffer

trait TwirlPlayCodeGen extends CodeGenerationTrait {

  def generate(models: ListBuffer[Bean]): String = {

    println(com.play.json.txt.playjson(models.toList).body.split("\n").toList)

    val output = com.play.json.txt.playjson(models.toList).body.split("\n").drop(1).mkString("\n").replaceAll("#newline\\s*", "\n")
    output
  }

}

