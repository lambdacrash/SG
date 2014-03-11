package org.lambda.graph

import scala.collection.mutable._
import scala.collection.mutable

/**
 * Created by bonfroy on 06/03/14.
 */
case class Graph[U](_directed: Boolean) {
  private val directed: Boolean = _directed
  private val nodes: HashMap[Int, Node[U]] = new HashMap[Int, Node[U]]
  private val edges: HashMap[Int, Edge[U]] = new HashMap[Int, Edge[U]]
  private var nodeCount: Int = 0
  private var edgeCount: Int = 0


  def getNodeCount() = nodeCount

  def getEdgeCount() = edgeCount

  def +=(e: Edge[U]) = addEdge(e)

  def +=(n: Node[U]) = addNode(n)

  def -=(e: Edge[U]) = removeEdge(e)

  def -=(n: Node[U]) = removeNode(n)

  def getNode(id: Int) = nodes(id)

  def getEdge(id: Int) = edges(id)

  def getEndPoints(id: Int) = (nodes(edges(id).getFromId()), nodes(edges(id).getToId()))

  def getEndPointsId(id: Int) = (edges(id).getFromId(), edges(id).getToId())

  def getOtherEndPoint(eid: Int, nid: Int) = nodes(edges(eid).getOther(nid))

  def getOtherEndPointId(eid: Int, nid: Int) = edges(eid).getOther(nid)

  def getNeighbors(n: Int) = for {x <- this.getNeighborsIds(n)} yield this.nodes(x)

  def getNeighborsIds(n: Int) = for {edge <- this.nodes(n).getEdges()} yield this.edges(edge).getOther(n)

  def dfs(start: Int): ListBuffer[Int] = {
    var search = new ListBuffer[Int]()
    val stack = new mutable.Stack[Int]()
    stack.push(start)
    while (stack.nonEmpty) {
      var u = stack.pop()
      search :+= u
      for (v <- getNeighborsIds(u)) {
        if (!search.contains(v) && !stack.contains(v) && u != v) {
          stack.push(v)
        }
      }
    }
    return search
  }

  def bfs(start: Int): ListBuffer[Int] = {
    var search = new ListBuffer[Int]()
    val queue = new mutable.Queue[Int]()
    queue.enqueue(start)
    while (queue.nonEmpty) {
      var u = queue.dequeue()
      search :+= u
      for (v <- getNeighborsIds(u)) {
        if (!search.contains(v) && !queue.contains(v) && u != v) {
          queue.enqueue(v)
        }
      }
    }
    return search
  }

  def addNode(node: Node[U]): Boolean = {
    if (node == null || nodes.contains(node.getId())) return false
    nodeCount += 1
    nodes += (node.getId() -> node)
    return true
  }

  def addNode(id: Int, data: U): Boolean = {
    val n: Node[U] = new Node[U](id, data)
    return addNode(n)
  }

  def addEdge(e: Edge[U]): Boolean = {
    if (e == null) return false
    edgeCount += 1
    edges += (e.getId() -> e)
    if (directed) {
      nodes(e.getFromId()).addEdge(e.getId())
    } else {
      nodes(e.getFromId()).addEdge(e.getId())
      nodes(e.getToId()).addEdge(e.getId())
    }
    return true
  }

  def addEdge(eid: Int, data: U, from: Node[U], to: Node[U]): Boolean = {
    if (from == null || to == null) return false
    val e: Edge[U] = new Edge[U](eid, data, from.getId(), to.getId())
    edges += (eid -> e)
    if (directed) {
      nodes(e.getFromId()).addEdge(e.getId())
    } else {
      nodes(e.getFromId()).addEdge(e.getId())
      nodes(e.getToId()).addEdge(e.getId())
    }
    return true
  }

  def removeNode(n: Node[U]): Boolean = {
    removeNode(n.getId())
  }

  def removeNode(nid: Int): Boolean = {
    if (nodes.contains(nid)) {
      val toRemove: HashSet[Edge[U]] = new HashSet[Edge[U]]()
      edges.foreach {
        case (eid: Int, e: Edge[U])
        =>
          if (e.getFromId() == nid) {
            toRemove += e
            nodes(e.getToId()).removeEdge(e.getId())
            nodes(e.getFromId()).removeEdge(e.getId())
          }
          if (e.getToId() == nid) {
            toRemove += e
            nodes(e.getToId()).removeEdge(e.getId())
            nodes(e.getFromId()).removeEdge(e.getId())
          }
      }
      toRemove.foreach {
        case (e: Edge[U]) => edges.remove(e.getId())
      }
      nodeCount -= 1
      nodes -= nid
      return true
    }
    else {
      return false
    }
  }

  def removeEdge(e: Edge[U]): Boolean = {
    removeEdge(e.getId())
  }

  def removeEdge(eid: Int): Boolean = {
    if (!edges.contains(eid)) return false
    val (u: Node[U], v: Node[U]) = getEndPoints(eid)
    edges -= eid;
    u.removeEdge(eid)
    v.removeEdge(eid)
    edgeCount -= 1
    return true
  }

  def prettyPrint() = {
    println("\nVertices")
    nodes.foreach {
      case (id: Int, n: Node[U]) => print(n.toStr() + " ")
        n.getEdges().foreach {
          case (eid: Int) => print(edges(eid).getId() + ":(" + edges(eid).getFromId() + "," + edges(eid).getToId() + ") ")
        }
        print("\n")
    }
    println("\nEdges")
    edges.foreach {
      case (id: Int, e: Edge[U]) => print("Edge[" + id + "] " + e.getId() + ":(" + e.getFromId() + "," + e.getToId() + ")\n")
    }
  }
}
