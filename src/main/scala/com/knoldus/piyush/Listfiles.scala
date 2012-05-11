package com.knoldus.piyush

object Listfiles extends Application{

  val fileshere= (new java.io.File(".")).listFiles()
  for(file <- fileshere)
  {
    println(file)
    
    
    
  }
  
  
}