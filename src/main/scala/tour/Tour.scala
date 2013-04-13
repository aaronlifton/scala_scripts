package main.scala.tour

import scala.collection.mutable.ListBuffer
import scala.math.{sqrt, pow, abs}
import scala.collection.mutable

//import scalaz._
//import Scalaz._

object Tour extends App {

  def clean(line: String): List[Double] = line.split(" ").map(_.toDouble).toList

  var dims = ListBuffer[Either[Boolean, Double]]()

  def dim(line: String): Either[Boolean, Double] = {
    try Right(line.toInt)
    catch {
      case e: Throwable => Left(false)
    }
  }

  var paths = new mutable.HashMap[Int, ListBuffer[List[Double]]]()

  //  Iterator.continually(Console.readLine).takeWhile(_ != "")
  "3\n1 3 5\n2 4 6\n3 4.5 7".split("\n").foreach {
    line =>
      if (line.length == 1) {
        dims += dim(line)
        paths(dim(line) match {
          case Right(i: Double) => i.toInt
        }) = ListBuffer[List[Double]]()
      } else {
        var b = paths.getOrElse(line.split(" ").length, -1)
        //try {
        val ln = line.split(" ").length
        paths(ln) += clean(line)
        //} catch { case e => "Error: " + e.getMessage }
      }
  }

  def euclideanDist(point1: List[Double], point2: List[Double]): Double = {
    sqrt(point1.zip(point2).foldLeft(0.0) {
      case (sum, (v1, v2)) => sum + pow(v1 - v2, 2)
    })
  }

  def euc(p: ListBuffer[List[Double]]): ListBuffer[Double] = {
    p.transpose.map(r => pow(r.sum, 2)).map(sqrt(_)) //.map(s => sqrt(abs(s.foldRight(0.0)(_ - _)))).toList
  }

  val p: List[List[Double]] = List(List(1.0, 3.0, 5.0), List(2.0, 4.0, 6.0), List(3.0, 4.5, 7.0))
  val eucSum = p.zipWithIndex.foldLeft(0.0) {
    case (c, (value, index)) =>
      val i = (if (0 < index && p.length > index) index - 1 else index)
      c + euclideanDist(p(i), value)
  }

  println(eucSum)

  println(paths.map(_._2.toList).map(_.transpose))

  //def route: Lens[Person, String] = Lens(_.name, (p, n) => p.copy(name = n))
  val euclid = paths.map(_._2)
    .map(_.transpose.map(r => sqrt(pow(r.sum, 2))))
    .map(s => sqrt(abs(s.foldRight(0.0)(_ - _))))
    .toList

  val manhattan = paths.map(_._2.transpose)
    .map(_.toList.map(r => abs(r.reduceLeft(_ - _))))
    .map(r => r.reduceRight(_ + _))
    .toList

  println("manhattan: %s".format(manhattan))
  println("euclid: %s".format(euclid))

  val sums = paths.map(_._2.transpose.map(p => (p :\ 0.0) {_ + _}))

  //  println(paths.map(_._1) <|*|> paths.map(_._2))

  val sumsMap = paths.map(_._2).zip(sums).toSeq.map(p => p._1 -> p._2).sortBy(_._2.sum).toMap
  val sumsMap2 = paths.map(_._2).zip(sums)
  println("(Path, Sum): %s".format(sumsMap2))

  dims.indices.foreach(d =>
    dims(d) match {
      case Right(i: Double) => "Instance " + (d + 1).toString + ": " + i
    })

  println(paths)

}