package com.knoldus.piyush

object f extends App {
  var a = List(List(1, 2, 3), List(4, 5, 6), List(6, 7, 8))
  var cols = List(0, 1)
  def removeColumns(lines: List[List[Int]], cols: List[Int]) {
   
      
        a = lines map { line =>
          var temp=line.remove(line.indexOf(_) == cols(0))
          temp.remove(temp.indexOf(_) == cols(1))
        }

    
    println(a)
  }
  removeColumns(a, cols)

}