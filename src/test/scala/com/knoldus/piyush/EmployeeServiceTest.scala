package com.knoldus.piyush

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class EmployeeServiceTest extends FunSuite {
  val employeeService = new EmployeeService
  val employees = getEmployees

  test("Employee Service should sort by age decending") {

    val sortedEmployees = employeeService.sortByAgeDecOrder(employees)
    assert(33 === sortedEmployees(0).age)
    assert(26 === sortedEmployees(1).age)
    assert(23 === sortedEmployees(2).age)
  
  }

  test("employee Service should sort by age ascending") {

    val sortedEmployees = employeeService.sortByAgeInAscOrder(employees)
    assert(23 === sortedEmployees(0).age)
    assert(26 === sortedEmployees(1).age)
    assert(33 === sortedEmployees(2).age)

  }

  test("employee Service should sort by salary decending") {

    val sortedEmployees = employeeService.sortBysalaryInDecOrder(employees)
    assert(48000 === sortedEmployees(0).salary)
    assert(24000 === sortedEmployees(1).salary)
    assert(12000 === sortedEmployees(2).salary)
  
  }

  test("employee Service should sort by salary ascending") {

    val sortedEmployees = employeeService.sortBySalaryInAscOrder(employees)
    assert(12000 === sortedEmployees(0).salary)
    assert(24000 === sortedEmployees(1).salary)
    assert(48000 === sortedEmployees(2).salary)

  }

  test("employee Service should sort by name decending") {

    val sortedEmployees = employeeService.sortByNameInDecOrder(employees)
    assert("Pollok" === sortedEmployees(0).name)
    assert("James" === sortedEmployees(1).name)
    assert("David" === sortedEmployees(2).name)
 
  }

  test("employee Service should sort by name ascending") {

    val sortedEmployees = employeeService.sortByNameInAscOrder(employees)
    assert("David" === sortedEmployees(0).name)
    assert("James" === sortedEmployees(1).name)
    assert("Pollok" === sortedEmployees(2).name)
 
  }

  private def getEmployees: List[Employee] = {
    val FirstPerson = Employee("David", 23, 12000)
    val secondPerson = Employee("Pollok", 26, 24000)
    val thirdPerson = Employee("James", 33, 48000)
    List(FirstPerson, secondPerson, thirdPerson)
  }
}