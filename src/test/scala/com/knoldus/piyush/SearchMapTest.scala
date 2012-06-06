package com.knoldus.piyush

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SearchMapTest extends FunSuite {

 val SearchMap=new SearchMap
  
  test("search a Continent") {

    assert("Asia" === SearchMap.searchContinent("India"))
    assert("Europe" === SearchMap.searchContinent("Russia"))
  }
  test("find countries of the couninent") {

    assert(List(Country("India"), Country("Pak"), Country("SriLanka")) === SearchMap.getCountriesByAContinent("Asia"))

  }
}