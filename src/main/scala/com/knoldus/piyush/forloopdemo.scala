package com.knoldus.piyush

object forloopdemo {
  
  val name= new Array[String](4)     // Declaring an Array in scala
  name(0)="This"
    name(1)="is"
      name(2)="an"
        name(3)="array"
  
  def printarrayelem(args:Array[String]):Unit={                  //Creating a method named "printarrayelem" with return..
                                                                  // type "Unit" ,that will print out all the elements in array. 
  for(arg <- args)                                          // A sample for loop for printing all elements in array.
  println(arg)
}
  
  def main(args: Array[String]) {                       //The main method in scala
  printarrayelem(name)
  }
}
