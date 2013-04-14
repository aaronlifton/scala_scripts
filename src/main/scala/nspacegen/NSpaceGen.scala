package main.scala.nspacegen

import scala.collection.mutable.ArrayBuffer

object NSpaceGen extends App {

  type AA[B] = Array[Array[B]]
  def compareArrays[A](a1: AA[A], a2: AA[A]): AA[A] = {
   a1.filter(x => a2.exists(y => x.sameElements(y)))
  }

  val points1 = ArrayBuffer[Array[Int]]()
  val points2 = ArrayBuffer[Array[Int]]()
  var curr = 0

  Iterator.continually(Console.readLine).takeWhile(_ != "" && curr < 2).foreach {
    line => {
      try {
        val s = line.split(" ").map(_.toInt)
        curr match {
          case 0 => points1 += s
          case 1 => points2 += s
        }
      }
      catch { case e => "Error: " + e.getMessage }
      if (line == ".") curr += 1
    }
  }

  val matches = compareArrays[Int](points1.toArray, points2.toArray)

  if (matches.isEmpty) println("No matches found")
  else {
    println(matches.map(_.mkString(" ")).mkString("\n"))
    println("\n" + matches.length + " matches found")
  }
}