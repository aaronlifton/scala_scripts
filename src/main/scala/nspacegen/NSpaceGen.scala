package main.scala.nspacegen

import scala.util.Random

object NSpaceGen extends App {
  def random2dArray(dim1: Int, dim2: Int, max: Int): Array[Array[Int]] = {
    Array.fill(dim1, dim2)(Random.nextInt(max))
  }

  type AA[B] = Array[Array[B]]
  def compareArrays[A](a1: AA[A], a2: AA[A]): AA[A] = {
   a1.filter(x => a2.exists(y => x.sameElements(y)))
  }

  val a1 = random2dArray(1000, 3, 100)
  val a2 = random2dArray(1000000, 3, 100)

  val matches = compareArrays[Int](a1, a2)

  if (matches.isEmpty) println("No matches found")
  else {
    println(matches.map(_.mkString(" ")).mkString("\n"))
    println("\n" + matches.length + " matches found")
  }
}