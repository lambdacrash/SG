package org.lambda.graph

import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import org.junit.runner.RunWith

/**
 * Created by bonfroy on 06/03/14.
 */
@RunWith(classOf[JUnitRunner])
class Graph extends FunSuite {
  test("TwoPlusTwo") {
    val twoPlusTwo = 2 + 2
    assert(twoPlusTwo == 4)
  }
}