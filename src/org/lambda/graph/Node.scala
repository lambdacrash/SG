package org.lambda.graph

import scala.collection.mutable.HashSet

/**
 * Created by bonfroy on 06/03/14.
 */
case class Node[T](_id: Int, _data: T) {
  private val data: T = _data
  final val id: Int = _id
  private val edges: HashSet[Int] = new HashSet[Int]()

  def getId(): Int = id

  def getData(): T = data

  def addEdge(id: Int) = edges += id
  def getEdges(): Array[Int] = edges.toArray
  def getDegree(): Int = edges.size

  def toStr() = "Node[" + id + "]=" + data + " degree=" + getDegree() + " edges:"

  def removeEdge(id: Int) = edges.remove(id)
}
