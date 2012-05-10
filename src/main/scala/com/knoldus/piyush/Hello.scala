package com.knoldus.piyush

class Hello
{
  
def calculatesum(x:Int,y:Int):Int={x + y}  

}



object Hello extends App {
  
  
 val a:Int= args(0).toInt
 val b:Int = args(1).toInt
 val h=new Hello()
 val hw= new SayHelloAgain()
 val c:Int= h.calculatesum(a,b)
println(c) 
	hw.hello("Hello Again")
	
	
	
	
}


class SayHelloAgain
{
  
def hello(S:String)={ println(S)}



}