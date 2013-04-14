package test.scala

import org.scalatest.FunSuite
import main.scala.nspacegen.NSpaceGen.{compareArrays, AA}
import scala.util.Random

class NSpaceGenSuite extends FunSuite {

  def random2dArray(dim1: Int, dim2: Int, max: Int): AA[Int] = {
    Array.fill(dim1, dim2)(Random.nextInt(max))
  }

  val a1 = random2dArray(1000, 3, 100)
  val a2 = random2dArray(1000000, 3, 100)

  test("arrays generated with correct sizes") {
    assert(a1.length == 1000)
    assert(a1(0).length == 3)
  }

  test("arrays have correct values") {
    val tb = random2dArray(1000, 3, 100)
    val min = tb.map(_.min).min
    val max = tb.map(_.max).max
    assert(min >= 0)
    assert(max <= 100)
  }

  test("arrays with same first element find it as a match") {
    val tb = random2dArray(1000, 3, 100)
    val td = random2dArray(1000, 3, 100)
    val u = Array(1,2,3)
    val matches = compareArrays[Int](tb.updated(0, u), td.updated(0, u))
    assert(matches.exists(_.deep == u.deep))
  }

  test("two arrays with unique elements find no matches") {
    val tb = Array(Array(1,2,3), Array(2,3,4))
    val td = Array(Array(3,4,5), Array(6,7,2))
    val matches = compareArrays[Int](tb, td)
    assert(matches.isEmpty)
  }

  test("arrays size 1000 and 1000000 have at least 1 match") {
    val matches = compareArrays[Int](a1, a2)
    assert(!matches.isEmpty)
  }
}