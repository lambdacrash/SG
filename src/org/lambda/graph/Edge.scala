package org.lambda.graph

/**
 * Created by bonfroy on 06/03/14.
 */
class Edge[T](_id: Int, _data: T, _from: Int, _to: Int) {
  final val id: Int = _id
  private var data: T = _data
  private var from: Int = _from
  private var to: Int = _to

  def getId(): Int = id

  def getData(): T = data

  def getFromId(): Int = from

  def getToId(): Int = to
}
