package org.lambda.graph

import scala.collection.mutable.HashSet

/**
 * Created by bonfroy on 06/03/14.
 */
case class Node[T](_id: Int, _data: T) {
  private val data: T = _data
  final val id: Int = _id
  private val edges: HashSet[Int] = new HashSet[Int]()

  def getId() = id

  def getData() = data

  def addEdge(eid: Int) = edges += eid

  def getEdges() = edges.toArray

  def getDegree() = edges.size

  def toStr() = "Node[" + id + "]=" + data + " degree=" + getDegree() + " edges:"

  def removeEdge(eid: Int) = edges.remove(eid)
}
