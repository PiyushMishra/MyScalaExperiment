package com.knoldus.piyush

object k extends App {
  var a = List(1, 2, 3)
  var b = List(1, 2, 3)
  var c = List(List(1, 2, 3), List(4, 5, 6), List(6, 7, 8))
  a = a.remove(a.indexOf(_) == 2)
  print(a)
  
  c = c map (c=>c.filterNot(c.indexOf(_) == 2))
  println(b)
  println(c)
}
