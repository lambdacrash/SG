package org.lambda.graph

import scala.collection.mutable.HashMap

/**
 * Created by bonfroy on 06/03/14.
 */
class Graph[U, V](_directed: Boolean) {
  private var directed: Boolean = _directed
  private val nodes: HashMap[Int, Node[U]] = new HashMap[Int, Node[U]]
  private val edges: HashMap[Int, Edge[V]] = new HashMap[Int, Edge[V]]

  def addNode(node: Node[U]): Boolean = {
    if (node == null || nodes.contains(node.getId())) return false
    nodes += (node.getId() -> node)
    return true
  }

  def getNode(id: Int): Node[U] = {
    nodes(id)
  }

  def getEdge(id: Int): Edge[V] = {
    edges(id)
  }

  def addEdge(e: Edge[V]): Boolean = {
    if (e == null) return false
    edges += (e.getId() -> e)
    if (directed) {
      nodes(e.getFromId()).addEdge(e.getId())
    } else {
      nodes(e.getFromId()).addEdge(e.getId())
      nodes(e.getToId()).addEdge(e.getId())
    }
    return true
  }

  def addEdge(id: Int, data: V, from: Node[U], to: Node[U]): Boolean = {
    if (from == null || to == null) return false
    val e: Edge[V] = new Edge[V](id, data, from.getId(), to.getId())
    edges += (id -> e)
    if (directed) {
      nodes(e.getFromId()).addEdge(e.getId())
    } else {
      nodes(e.getFromId()).addEdge(e.getId())
      nodes(e.getToId()).addEdge(e.getId())
    }
    return true
  }

  def prettyPrint() = {
    nodes.foreach {
      case (id: Int, n: Node[U]) => print(n.toStr() + " ")
        n.getEdges().foreach {
          case (id: Int) => print("(" + edges(id).getFromId() + "," + edges(id).getToId() + ") ")
        }
        print("\n")
    }
  }
}
