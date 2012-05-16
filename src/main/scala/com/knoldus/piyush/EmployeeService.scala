package com.knoldus.piyush

class EmployeeService {
  def sortByAgeInAscOrder(employees: List[Employee]): List[Employee] = employees.sort(_.age < _.age)
  
  def sortByAgeDecOrder(employees: List[Employee]): List[Employee] = employees.sort(_.age > _.age)
  
  def sortBySalaryInAscOrder(employees: List[Employee]): List[Employee] = employees.sort(_.salary < _.salary)
  
  def sortBysalaryInDecOrder(employees: List[Employee]): List[Employee] = employees.sort(_.salary > _.salary)
  
  def sortByNameInAscOrder(employees: List[Employee]): List[Employee] = employees.sort(_.name < _.name)
  
  def sortByNameInDecOrder(employees: List[Employee]): List[Employee] = employees.sort(_.name > _.name)
}