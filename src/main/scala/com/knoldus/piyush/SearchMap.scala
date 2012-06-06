package com.knoldus.piyush

class SearchMap {

  val world = WorldMap.getWorldMap

  def getCountriesByAContinent(continentName: String): List[Country] = {
    var ListCountries = List[Country]()
    world.Continents foreach {

      continent =>
        if (continent.name.equals(continentName)) {

          ListCountries = continent.Countries
        }
    }
    ListCountries
  }

  def searchContinent(countryName: String): String = {
    var foundContinent = ""
    world.Continents foreach {

      continent =>
        continent.Countries foreach {
          country =>
            if (country.name == countryName) {
              foundContinent = continent.name

            }

        }

    }
    foundContinent
  }

}

