package main.scala.nspacegen

import scala.util.Random
import scala.collection.mutable.ArrayBuffer

object NSpaceGen extends App {
  def n_rands(n: Int, r: Int) = {
    Seq.fill(n)(Random.nextInt)
  }

  var rands = n_rands(10, 3)

  def randomArray(n: Int): Array[Int] = {
    val range = 0 to 100
    var a: Array[Int] = new Array[Int](n)

    def getRndInt(range: Range): Int = {
      val rnd = new scala.util.Random
      rnd.nextInt(range.length)
    }

    for (index <- 0 to a.length - 1) a(index) = (getRndInt(range))
    a
  }

  def randomArrays(n: Int, r: Int): ArrayBuffer[Array[Int]] = {
    var a = ArrayBuffer[Array[Int]]()
    for (i <- 0 to n) a += randomArray(r)
    return a
  }

  //var point = randomArray(3)

  val thous = randomArrays(1000, 3)
  val milli = randomArrays(10000, 3)

  def compareArrays(mil: ArrayBuffer[Array[Int]], thous: ArrayBuffer[Array[Int]]): ArrayBuffer[Array[Int]] = {
    var matched = ArrayBuffer[Array[Int]]()
    mil.foreach {
      m =>
        thous.foreach {
          t =>
            if (m.deep == t.deep) {
              matched += t
            }
        }
    }
    matched
  }

  val matches = compareArrays(milli, thous)
  if (matches.length == 0) println("No matches found")
  else {
    println(matches.map(_.mkString(" ")).mkString("\n"))
    println("\n" + matches.length + " matches found")
  }
}