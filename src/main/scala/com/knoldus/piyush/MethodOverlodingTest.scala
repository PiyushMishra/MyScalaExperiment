package com.knoldus.piyush

class MethodOverlodingTest {
  def calculate(x: Int,y:String) = { println(x + y) }
  def calculate(s: String):String = s
  def calculate(y: Float) = { println(y) }
}

object MethodOverlodingTest extends App {
  val m = new MethodOverlodingTest()
 println( m.calculate("I m from Overloding"))
  m calculate(10)
  
  m calculate(10,"string")
  val mo = new MethodOverriding()
  println(mo.calculate("i m from method overriding"))
}

class MethodOverriding extends MethodOverlodingTest {

  override def calculate(s: String):String =s+"neel"

}