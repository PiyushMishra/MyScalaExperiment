package com.knoldus.piyush

abstract class AbstractExample {

  def printsomething(S:String)
  def sum(x:Int,y:Int)=println(x+y)
}


class Concrete extends AbstractExample with Mytrait
{
 def printsomething(S:String)=println("hello " + S)
 def sum(x:Int,y:Int,z:Int)=println(x + y + z)
}

object Concrete extends App 
{
   
val myconcreteclassobject = new Concrete();
 val myconcreateclaaobject1:AbstractExample = new Concrete()
 
myconcreteclassobject.printsomething("piyush") 
myconcreateclaaobject1.printsomething("dear")
myconcreteclassobject.sum(4,5)
myconcreateclaaobject1.sum(4,5)
myconcreteclassobject.sum(4,5,6)
}