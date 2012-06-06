package com.knoldus.piyush
import scala.collection.mutable.Map
import sun.nio.ch.Reflect
object mutability extends App{
 
  var map= Map("1"->"fsdfds")
  map= map + ("2"->"12333")
}