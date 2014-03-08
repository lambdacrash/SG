package org.lambda.graph

/**
 * Created by bonfroy on 06/03/14.
 */
case class Edge[T](_id: Int, _data: T, _from: Int, _to: Int) {
  final val id: Int = _id
  private val data: T = _data
  private val from: Int = _from
  private val to: Int = _to

  def getId() = id

  def getData() = data

  def getFromId() = from

  def getToId() = to

  def getOther(nid: Int): Int = {
    if (from == nid) return to
    else return from
  }
}
