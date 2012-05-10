package com.knoldus.piyush

class Rational(n:Int) {
   

 override def toString()="created" + n 
  
  def +(that:Int):Int={that + this.n}


}


object Rational extends App {
  
  
val firstrational= new Rational(3) 

val thirdRatioanl:Int= firstrational + (9)
println(thirdRatioanl)	
	
}


