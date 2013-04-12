package main.scala.buffalo

import scala.collection.mutable.ListBuffer
import scala.collection.immutable.IndexedSeq

object Buffalo extends App {

  // val input = readLine("prompt> ")
  var a = ListBuffer[Seq[Int]]()
  Iterator.continually(Console.readLine).takeWhile(_ != "").foreach {
    line =>
      val processed = line.split(" ").map(_.toInt).toList
      if (processed.length == 8) a += processed
  }

  //-3 -3 0 6 3 -3 0 0
  def ranchMax(ranch: Seq[Int]): Seq[(Int, Int)] = {
    val coords = ranch.grouped(2).collect {
      case List(a, b) => (a, b)
    }.toIndexedSeq
    println(coords)
    val fenceCoords: IndexedSeq[(Int, Int)] = coords.dropRight(1)
    val xmax: Int = fenceCoords.map(_._1).max
    val ymax: Int = fenceCoords.map(_._2).max
    val xmin: Int = fenceCoords.map(_._1).min
    val ymin: Int = fenceCoords.map(_._2).min
    val maxes: Seq[(Int, Int)] = Seq((xmin, ymin), (xmax, ymax))
    maxes
  }

  def dirs(ranch: Seq[Int]): IndexedSeq[String] = {
    val coords = ranch.grouped(2).collect {
      case List(a, b) => (a, b)
    }.toList
    val fenceCoords = coords.dropRight(1)
    val dirs: IndexedSeq[String] = fenceCoords.map {
      c =>
        if (coords(3)._1 > c._1) "L" else "R" // if x < xmin
        if (coords(3)._2 > c._2) "L" else "R" // if y < ymin
    }.toIndexedSeq
    dirs
  }

  val ranchMaxes = a.map(ranchMax(_))
  val ranchDirs = a.map(dirs(_))

  println(ranchMaxes)
  println(ranchDirs)
}
