package com.knoldus.piyush

object EmployeeServiceApp extends App  {
  val MyEmployees: List[Employee] = getEmployees
  val EmployeeService = new EmployeeService

  val sortedByAgeInAscOrder: List[Employee] = EmployeeService.sortByAgeInAscOrder(MyEmployees)
  println("This is by Asc Age");
  sortedByAgeInAscOrder foreach (Employee => println(Employee.name, Employee.age, Employee.salary))

  val sortedByAgeInDecOrder: List[Employee] = EmployeeService.sortByAgeDecOrder(MyEmployees)
  println("This is by Dec Age");
  sortedByAgeInDecOrder foreach (Employee => println(Employee.name, Employee.age, Employee.salary))

  val sortedBySalaryInAscOrder: List[Employee] = EmployeeService.sortBySalaryInAscOrder(MyEmployees)
  println("This is by Asc salary");
  sortedBySalaryInAscOrder foreach (Employee => println(Employee.name, Employee.age, Employee.salary))

  val sortedBySalaryInDecOrder: List[Employee] = EmployeeService.sortBysalaryInDecOrder(MyEmployees)
  println("This is by Dec Salary");
  sortedBySalaryInDecOrder foreach (Employee => println(Employee.name, Employee.age, Employee.salary))

  val sortedByNameInAscOrder: List[Employee] = EmployeeService.sortByNameInAscOrder(MyEmployees)
  println("This is by Asc Name");
  sortedByNameInAscOrder foreach (Employee => println(Employee.name, Employee.age, Employee.salary))

  val sortedByNameIndecOrder: List[Employee] = EmployeeService.sortByNameInDecOrder(MyEmployees)
  println("This is by Asc Name");
  sortedByNameIndecOrder foreach (Employee => println(Employee.name, Employee.age, Employee.salary))

  private def getEmployees: List[Employee] = {
    val FirstPerson = Employee("David", 23, 12000)
    val secondPerson = Employee("Pollok", 26, 24000)
    val thirdPerson = Employee("James", 33, 48000)

    List(FirstPerson, secondPerson, thirdPerson)
  }
}