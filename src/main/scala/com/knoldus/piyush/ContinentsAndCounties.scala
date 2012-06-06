package com.knoldus.piyush

class ContinentsAndCounties {

  val continents = List("Asia", "Africa", "Europe", "America")
  val Asia = List("india", "pak", "srilanka")
  val Africa = List("namibia", "nairobi", "africa")
  val Europe = List("russia", "england", "germony")
  val America = List("canada", "mexico", "usa")

  var worldMap: Map[String, List[String]] = Map()

  val ContinentsWiseListOfCounties: List[List[String]] = List(Asia, Africa, Europe, America)

  continents foreach {
    var index = 0
    continent => worldMap += (continent -> ContinentsWiseListOfCounties(index))
    index = index + 1
  }

  def getCountriesByAContinent(continent: String) = worldMap(continent) foreach (println)

  def searchContinent(country: String) = {
    var foundContinent: String = ""
    continents foreach { continent =>
      if (worldMap(continent).exists(_ == country) == true)
        foundContinent = continent
    }
  }

}
object ContinentsAndCounties extends App {

  val CAC = new ContinentsAndCounties
  println("Enter 1 to serach Continent By A Country Name")
  println("Enter 2 to get  Countries By A Continent Name")

  val choice = Console.readInt
  println("Enter Name")
  val name = Console.readLine

  if (choice == 1) CAC.searchContinent(name)
  else CAC.getCountriesByAContinent(name)

}
