package org.lambda.graph

import scala.collection.mutable.HashMap
import scala.collection.mutable

/**
 * Created by bonfroy on 06/03/14.
 */
case class Graph[U](_directed: Boolean) {
  private val directed: Boolean = _directed
  private val nodes: HashMap[Int, Node[U]] = new HashMap[Int, Node[U]]
  private val edges: HashMap[Int, Edge[U]] = new HashMap[Int, Edge[U]]

  def +=(e: Edge[U]): Boolean = addEdge(e)

  def +=(n: Node[U]): Boolean = addNode(n)

  def -=(e: Edge[U]): Boolean = removeEdge(e)

  def -=(n: Node[U]): Boolean = removeNode(n)

  def getNode(id: Int): Node[U] = nodes(id).copy()

  def getEdge(id: Int): Edge[U] = edges(id).copy()

  def getEndPoints(id: Int): (Node[U], Node[U]) = (nodes(edges(id).getFromId()).copy(), nodes(edges(id).getToId()).copy())

  def addNode(node: Node[U]): Boolean = {
    if (node == null || nodes.contains(node.getId())) return false
    nodes += (node.getId() -> node)
    return true
  }

  def addNode(id: Int, data: U): Boolean = {
    val n: Node[U] = new Node[U](id, data)
    return addNode(n)
  }

  def addEdge(e: Edge[U]): Boolean = {
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

  def addEdge(id: Int, data: U, from: Node[U], to: Node[U]): Boolean = {
    if (from == null || to == null) return false
    val e: Edge[U] = new Edge[U](id, data, from.getId(), to.getId())
    edges += (id -> e)
    if (directed) {
      nodes(e.getFromId()).addEdge(e.getId())
    } else {
      nodes(e.getFromId()).addEdge(e.getId())
      nodes(e.getToId()).addEdge(e.getId())
    }
    return true
  }

  def removeNode(n: Node[U]): Boolean = removeNode(n.getId())

  def removeNode(id: Int): Boolean = {
    if (nodes.contains(id)) {
      val toRemove: mutable.HashSet[Edge[U]] = new mutable.HashSet[Edge[U]]()
      edges.foreach {
        case (eid: Int, e: Edge[U])
        =>
          if (e.getFromId() == id) {
            toRemove += e
            nodes(e.getToId()).removeEdge(eid)
            nodes(e.getFromId()).removeEdge(eid)
          }
          if (e.getToId() == id) {
            toRemove += e
            nodes(e.getToId()).removeEdge(eid)
            nodes(e.getFromId()).removeEdge(eid)
          }
      }
      toRemove.foreach {
        case (e: Edge[U]) => print("remove e=" + e.getId() + "\n"); edges.remove(e.getId())
      }
      nodes -= id
      return true
    }
    else {
      return false
    }
  }

  def removeEdge(e: Edge[U]): Boolean = removeEdge(e.getId())

  def removeEdge(id: Int): Boolean = {
    if (!edges.contains(id)) return false
    val (u: Node[U], v: Node[U]) = getEndPoints(id)
    edges -= id;
    u.removeEdge(id)
    v.removeEdge(id)
    print("u=" + u.getId() + " v=" + v.getId())
    return true
  }

  def prettyPrint() = {
    println("\nVertices")
    nodes.foreach {
      case (id: Int, n: Node[U]) => print(n.toStr() + " ")
        n.getEdges().foreach {
          case (eid: Int) => print("(" + edges(eid).getFromId() + "," + edges(eid).getToId() + ") ")
        }
        print("\n")
    }
    println("\nEdges")
    edges.foreach {
      case (id: Int, e: Edge[U]) => print("Edge[" + id + "] (" + e.getFromId() + "," + e.getToId() + ")\n")
    }
  }
}
