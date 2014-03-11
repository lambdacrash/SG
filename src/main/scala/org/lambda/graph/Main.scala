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
    g.addNode(new Node[Int](4, 4))
    g.addNode(new Node[Int](5, 4))
    g.addNode(new Node[Int](6, 4))
    g.addEdge(10, 8, g.getNode(1), g.getNode(3))
    g.addEdge(11, 5, g.getNode(1), g.getNode(2))
    g.addEdge(12, 5, g.getNode(2), g.getNode(4))
    g.addEdge(13, 5, g.getNode(4), g.getNode(5))
    g.addEdge(14, 5, g.getNode(4), g.getNode(6))
    g.addEdge(15, 5, g.getNode(5), g.getNode(6))
    println("Construction")
    g.prettyPrint()
    println("\nGet neighbors of 1")
    for (x <- g.getNeighborsIds(1)) print("1-" + x + " ")
    println()

    println("\nBFS from 1")
    for (x <- g.bfs(1)) print(x + " ")
    println()

    println("\nDFS from 1")
    for (x <- g.dfs(1)) print(x + " ")
    println()

    println("\nRemove node 3")
    g.removeNode(3)
    g.prettyPrint()

    println("\nRemove edge 11")
    g.removeEdge(11)
    g.prettyPrint()
  }
}
