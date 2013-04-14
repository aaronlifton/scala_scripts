package main.scala.nspacegen

import scala.collection.mutable.ArrayBuffer
import scala.math.{abs, pow, sqrt}

object NSpaceGen extends App {

  type AA[B] = Array[Array[B]]
  type ABA[A] =  ArrayBuffer[Array[A]]

  def compareArrays[A](a1: AA[A], a2: AA[A]): AA[A] = {
    a1.filter(x => a2.exists(y => x.sameElements(y)))
  }

  val modes = List((1, "Compare Arrays"), (2, "Closest Point"))
  println(modes.map(x => x._1.toString + ": " + x._2).mkString("\n"))

  def detMode(c: Int = -1) {
    c match {
      case 1 => compareMode
      case 2 => closestMode
      case _ =>
        val line = readLine(">")
        try {
          detMode(line.toInt)
        }
        catch {
          case e: Throwable =>
            "Error: " + e.getMessage
            detMode()
        }
    }
  }

  detMode()

  def compareMode = {
    val points1 = ArrayBuffer[Array[Double]]()
    val points2 = ArrayBuffer[Array[Double]]()
    var curr = 0

    Iterator.continually(Console.readLine).takeWhile(_ != "" && curr < 2).foreach {
      line => {
        try {
          val s = line.split(" ").map(_.toDouble)
          curr match {
            case 0 => points1 += s
            case 1 => points2 += s
          }
        }
        catch { case e => "Error: " + e.getMessage }
        if (line == ".") curr += 1
      }
    }

    val matches = compareArrays[Double](points1.toArray, points2.toArray)

    if (matches.isEmpty) println("No matches found")
    else {
      println(matches.map(_.mkString(" ")).mkString("\n"))
      println("\n" + matches.length + " matches found")
    }
  }

  def closestMode = {
    /* TODO */
    val points = ArrayBuffer[Array[Double]]()
    var origin = Array[Double]()
    var curr = 0

    Iterator.continually(Console.readLine).takeWhile(_ != "" && curr < 2).foreach {
      line => {
        try {
          val s = line.split(" ").map(_.toDouble)
          curr match {
            case 0 => points += s
            case 1 => origin = s
          }
        }
        catch { case e => "Error: " + e.getMessage }
        if (line == ".") curr += 1
      }
    }

    def euclideanDist(point1: Array[Double], point2: Array[Double]): Double = {
      sqrt(point1.zip(point2).foldLeft(0.0) {
        case (sum, (v1, v2)) => sum + pow(v1 - v2, 2)
      })
    }

    def zipWithEuclidianDist(a1: AA[Double], a2: Array[Double]) = {
      a1.map(y => (y, euclideanDist(y, a2))).toArray
    }

//    origin = Array(1,2,3).map(_.toDouble)

    val zpoints = zipWithEuclidianDist(points.toArray, origin)
    println(zpoints.map(a => (a._1.toList, a._2)).mkString("\n"))
    val closest = zpoints.sortWith(_._2 < _._2).head
    println("Closest point: %s\nDistance: %s".format(closest._1.toList, closest._2))
  }
}