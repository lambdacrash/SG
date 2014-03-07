package org.lambda.graph

/**
 * Created by bonfroy on 06/03/14.
 */
object Main {
  def main(args: Array[String]): Unit = {
    val g = new Graph[Int](false)
    g.addNode(new Node[Int](0, 1))
    g.addNode(new Node[Int](1, 2))
    g.addNode(new Node[Int](2, 3))
    g.addNode(new Node[Int](3, 4))
    g.addEdge(10, 8, g.getNode(1), g.getNode(3))
    g.addEdge(11, 5, g.getNode(1), g.getNode(2))
    println("Construction")
    g.prettyPrint()
    println("\nRemove node 3")
    g.removeNode(3)
    g.prettyPrint()
    println("\nRemove edge 11")
    g.removeEdge(11)
    g.prettyPrint()
  }
}
