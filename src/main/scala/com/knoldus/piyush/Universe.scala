package com.knoldus.piyush

case class World(Continents: List[Continent])

case class Continent(name: String, Countries: List[Country])

case class Country(name: String)


class DDDD(countries: Map[String, List[Country]])