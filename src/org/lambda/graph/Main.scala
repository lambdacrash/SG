package org.lambda.graph

/**
 * Created by bonfroy on 06/03/14.
 */
object Main {
  def main(args: Array[String]): Unit = {
    var g = new Graph[Int, Int](false)
    g.addNode(new Node[Int](0, 1))
    g.addNode(new Node[Int](1, 2))
    g.addNode(new Node[Int](2, 3))
    g.addNode(new Node[Int](3, 4))
    g.addEdge(1, 8, g.getNode(1), g.getNode(3))
    g.prettyPrint()
  }
}
