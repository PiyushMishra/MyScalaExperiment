package com.knoldus.piyush

object WorldMap {
  def getWorldMap: World = {
    val AsianCountries = List(Country("India"), Country("Pak"), Country("SriLanka"))
    val EuropianCountries = List(Country("Russia"), Country("Ukraine"), Country("England"), Country("Germony"))
    val AmericanCountries = List(Country("America"), Country("Canada"), Country("Mexico"), Country("Urgentina"))
    val AfricanCountries = List(Country("Africa"), Country("Kenya"), Country("Namibia"))

    val Asia = Continent("Asia", AsianCountries)
    val Europe = Continent("Europe", EuropianCountries)
    val America = Continent("America", AmericanCountries)
    val Africa = Continent("Africa", AfricanCountries)
    val Continents = List(Asia, Europe, America, Africa)
    val world = World(Continents)
    world
  }

}