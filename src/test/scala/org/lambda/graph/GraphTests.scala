package org.lambda.graph

import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import scala.collection.mutable.ListBuffer

/**
 * Created by bonfroy on 06/03/14.
 */
@RunWith(classOf[JUnitRunner])
class GraphTests extends FunSuite {
  test("AddRemoveVertices") {
    val g = new Graph[Int](false)
    g.addNode(new Node[Int](0, 1))
    g.addNode(new Node[Int](1, 2))
    g.addNode(new Node[Int](2, 3))
    g.addNode(new Node[Int](3, 4))
    g.addNode(new Node[Int](4, 4))
    g.addNode(new Node[Int](5, 4))
    g.addNode(new Node[Int](6, 4))
    assert(g.getNodeCount() == 7)
    g.removeNode(0)
    g.removeNode(1)
    g.removeNode(2)
    assert(g.getNodeCount() == 4)
  }
  test("AddRemoveEdges") {
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
    assert(g.getNodeCount() == 7)
    assert(g.getEdgeCount() == 6)

    g.removeNode(3)
    assert(g.getNodeCount() == 6)
    assert(g.getEdgeCount() == 5)

    g.removeEdge(11)
    assert(g.getNodeCount() == 6)
    assert(g.getEdgeCount() == 4)
  }
  test("DFS") {
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
    assert(g.getNodeCount() == 7)
    assert(g.getEdgeCount() == 6)
    val dfs: ListBuffer[Int] = g.dfs(1)
    assert(dfs.length == 6)
  }
  test("BFS") {
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
    assert(g.getNodeCount() == 7)
    assert(g.getEdgeCount() == 6)
    val bfs: ListBuffer[Int] = g.bfs(1)
    assert(bfs.length == 6)
  }
}