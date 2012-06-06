package com.knoldus.piyush

object test {
   def countcharacter(a: String, b: String) = {                    //Declaring a method in scala
       if (a.length > b.length) println("The large word is" + a) 
       else  println("The large word is" + b) 
 }

  def main(args: Array[String]) {                                 // The main method in scala
      countcharacter("Neelkanth","Sachdeva")                      //calling the method
  }

}



