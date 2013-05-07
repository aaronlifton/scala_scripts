package main.scala.dotproduct

/**
 * Created with IntelliJ IDEA.
 * User: aaron
 * Date: 5/6/13
 * Time: 8:35 PM
 */

import scala.collection.mutable.ArrayBuffer
import scala.math.{abs, pow, sqrt, acos}

object DotProduct extends App {
  var a = ArrayBuffer[Double]()
  var b = ArrayBuffer[Double]()
  var numVectors: Int = 0
  var tempA, tempB, dotProduct, dotProductA, dotProductB, angleProduct = 0.0

  numVectors = readLine("number of vectors: ") match {
    case x: String => x.toInt
    case _ => 0
  }

  def fillArr(num: Int, arr: ArrayBuffer[Double]) = {
    var i = 0
    Iterator.continually(Console.readLine).takeWhile(_ != "" && i < num).foreach {
      line =>
        a += line.toDouble
        i = i + 1
    }
  }

  if (numVectors > 0) {
    fillArr(numVectors, a)
    fillArr(numVectors, b)


    (0 until numVectors).foreach { i: Int =>
     dotProduct += a(i) * b(i) // dot product of 2 vectors
     dotProductA += pow(2, a(i)) // vector length A
     dotProductB += pow(2, b(i)) // vector length B
    }
    val blah: Double = dotProduct / (dotProductA * dotProductB)
    angleProduct = acos(blah)

    println("Dot product: " + dotProduct.toString)
    println("Vector length A: " + dotProductA.toString)
    println("Vector length A: " + dotProductB.toString)
  }
}
