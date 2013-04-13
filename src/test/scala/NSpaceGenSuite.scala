package test.scala

import org.scalatest.FunSuite
import main.scala.nspacegen.NSpaceGen

class NSpaceGenSuite extends FunSuite {

  val ta: Array[Array[Int]] = NSpaceGen.random2dArray(1000, 3, 100)

  test("arrays generated with correct sizes") {
    assert(ta.length == 1000)
    assert(ta(0).length == 3)
  }

  test("arrays have correct values") {
    val tb: Array[Array[Int]] = NSpaceGen.random2dArray(1000, 3, 100)
    val min = tb.map(_.min).min
    val max = tb.map(_.max).max
    assert(min >= 0)
    assert(max <= 100)
  }
}