package org.vz.datasci

import scala.collection.mutable.ListBuffer

import com.play.json.TwirlPlayCodeGen

object TestApp extends App {

  val models = Set(
    Bean("Person") ~ "name:String" ~ "age: Int" ~ "freinds: Map[String, Address]" ~ "company: Company" ~ "person: Map[String, Person]" ^ ("Address", "Company"),
    Bean("Address") ~ "street: String" ~ "house: House" ^ ("House"),
    Bean("m") ~ "hero: String" ~ "prof: String" ~ "m: String",
    Bean("House") ~ "house_no: String",
    Bean("Company") ~ "name: String",
    Bean("Down") ~ "name: Option[String]" ~ "slop:Int" ~ "location: Company" ^ ("Address")
  )

  (new CodeGeneration with ArgonautCodeGeneration).write(models, "C:\\Users\\MISHRPI\\Documents\\iptv\\code-gen\\src\\main\\scala\\org\\vz\\datasci\\generated\\Models.scala")

  (new CodeGeneration with SparyCodeGen).write(models, "C:\\Users\\MISHRPI\\Documents\\iptv\\code-gen\\src\\main\\scala\\org\\vz\\datasci\\generated\\SparyModels.scala")

  (new CodeGeneration with PlayCodeGeneration).write(models, "C:\\Users\\MISHRPI\\Documents\\iptv\\code-gen\\src\\main\\scala\\org\\vz\\datasci\\generated\\PlayModels2.scala")

  (new CodeGeneration with TwirlPlayCodeGen).write(models, "C:\\Users\\MISHRPI\\Documents\\iptv\\code-gen\\src\\main\\scala\\org\\vz\\datasci\\generated\\PlayModels3.scala")
}

