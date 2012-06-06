package com.knoldus.piyush

import scala.collection.mutable.ArrayBuffer
object arraybuffdemo {
  val abuf = new ArrayBuffer[Int]()        //Array buffer
  val array = Array(1, 2, 3, 4, 5)         //Simple array

  def main(args: Array[String]) {

    abuf += 1                              //Inserting an element to buffer
    println(abuf)

    abuf ++= array                        //Inserting a whole array to arraybuffer
    println(abuf)

    abuf.trimStart(1)                     //removing one element from starting point in arraybuffer
    println(abuf)

    abuf.trimEnd(1)                        //removing one element from ending point in arraybuffer
    println(abuf)

    abuf.insert(1, 80, 81, 82)            //inserting the three element say 80,81,82 at index 1.
    println(abuf)
       
    abuf.remove(1, 2)                     //removing two elements from the index 1
    println(abuf)

 }
}
