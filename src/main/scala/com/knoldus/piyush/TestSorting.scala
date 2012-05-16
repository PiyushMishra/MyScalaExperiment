package com.knoldus.piyush

class TestSorting {
  
   def sortByAgeInAscOrder(unsorted:List[Employee]):List[Employee] ={ unsorted.sort(_.age<_.age)}
   def sortByAgeDecOrder(unsorted:List[Employee]):List[Employee] ={unsorted.sort(_.age>_.age)}
   def sortBySalaryInAscOrder(unsorted:List[Employee]):List[Employee] ={unsorted.sort(_.salary<_.salary)}
   def sortBysalaryInDecOrder(unsorted:List[Employee]):List[Employee] ={unsorted.sort(_.salary>_.salary)}
   def sortByNameInAscOrder(unsorted:List[Employee]):List[Employee] ={unsorted.sort(_.name<_.name)}
   def sortByNameInDecOrder(unsorted:List[Employee]):List[Employee] ={unsorted.sort(_.name>_.name)}
   
}


object TestSorting extends App{

  val FirstPerson= new Employee("David",23,12000)
  val secondPerson = new Employee("Pollok",26,24000)
  val thirdPerson = new Employee("James",33,48000)
  
  val MyEmployees:List[Employee]= List(FirstPerson,secondPerson,thirdPerson)
  val TestSorting= new TestSorting
 
  val sortedByAgeInAscOrder:List[Employee]=TestSorting.sortByAgeInAscOrder(MyEmployees)
  println("This is by Asc Age"); 
  sortedByAgeInAscOrder foreach(Employee => println(Employee.name,Employee.age,Employee.salary) )
 
  val sortedByAgeInDecOrder:List[Employee]=TestSorting.sortByAgeDecOrder(MyEmployees)
  println("This is by Dec Age");
  sortedByAgeInDecOrder foreach(Employee => println(Employee.name,Employee.age,Employee.salary) )
 
  val sortedBySalaryInAscOrder:List[Employee]=TestSorting.sortBySalaryInAscOrder(MyEmployees)
  println("This is by Asc salary");
  sortedBySalaryInAscOrder foreach(Employee => println(Employee.name,Employee.age,Employee.salary) )
  
  val sortedBySalaryInDecOrder:List[Employee]=TestSorting.sortBysalaryInDecOrder(MyEmployees)
  println("This is by Dec Salary");
  sortedBySalaryInDecOrder foreach(Employee => println(Employee.name,Employee.age,Employee.salary) )
  
  val sortedByNameInAscOrder:List[Employee]=TestSorting.sortByNameInAscOrder(MyEmployees)
  println("This is by Asc Name");
  sortedByNameInAscOrder foreach(Employee => println(Employee.name,Employee.age,Employee.salary) )
  
  
  val sortedByNameIndecOrder:List[Employee]=TestSorting.sortByNameInDecOrder(MyEmployees)
  println("This is by Asc Name");
  sortedByNameIndecOrder foreach(Employee => println(Employee.name,Employee.age,Employee.salary) )
  
  
  
  
}