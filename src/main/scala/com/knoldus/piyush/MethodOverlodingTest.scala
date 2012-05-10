package com.knoldus.piyush

class MethodOverlodingTest {
  def calculate(x: Int) = { println(x) }
  def calculate(s: String) = { println(s) }
  def calculate(y: Float) = { println(y) }
}

object MethodOverlodingTest extends App {
  val m = new MethodOverlodingTest()
  m.calculate("I m from Overloding")
  m.calculate(10)
  val mo = new MethodOverriding()
  mo.calculate("i m from method overriding")
}

class MethodOverriding extends MethodOverlodingTest {

  override def calculate(s: String) = { println(s) }

}