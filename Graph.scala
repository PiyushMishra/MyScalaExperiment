package org.vz.datasci

import scala.collection.mutable.ListBuffer

object Graph {

  val adjencyList = scala.collection.mutable.Map[Bean, ListBuffer[String]]()

  def findDependencyArrows(beans: Set[Bean]) = {
    beans flatMap { bean =>
      if (!bean.depends.isEmpty)
        bean.depends.map { x => bean -> x } else Set(bean -> "None")
    }
  }

  def build(dependencyArrows: Set[(Bean, String)]) = {
    dependencyArrows map {
      case (start, end) =>
        if (adjencyList.exists(p => p._1 == start))
          adjencyList.put(start, adjencyList(start) ++ ListBuffer(end))
        else
          adjencyList.put(start, ListBuffer(end))
    }
    adjencyList mapValues { edge => edge.-=("None") }
  }

  def inDegree(node: String) = adjencyList.count {
    case (a, b) => b.exists { p => p == node }

  }

  def resolveDependency(adjencyList: Map[Bean, ListBuffer[String]]) = {
    val sortedList = ListBuffer[Bean]()
    val unsortedList = scala.collection.mutable.Queue[Bean]()
    for {
      (startNode, edges) <- adjencyList
      if (inDegree(startNode.name) == 0)
    } yield unsortedList.+=(startNode)

    while (!unsortedList.isEmpty) {
      val node = unsortedList.dequeue()
      sortedList.+=(node)
      adjencyList(node).foreach { edge =>
        adjencyList(node) -= (edge)
        if (inDegree(edge) == 0) {
          unsortedList.+=(adjencyList.filter(_._1.name == edge).head._1)
        }
      }
    }
    if (!adjencyList.forall(_._2.isEmpty)) sys.error(""""graph has at least one cycle, look into your case classes 
                                                         if they depend on each other directly or indirectly""")
    else sortedList.reverse
  }

}