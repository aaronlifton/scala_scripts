
package main.scala.drunkard

import scala.collection.mutable.ArrayBuffer

object Drunkard extends App {
  // val input = readLine("prompt> ")
  var a: ArrayBuffer[Array[String]] = ArrayBuffer[Array[String]]()
  Iterator.continually(Console.readLine).takeWhile(_ != null).foreach(line => a += line.split(" "))

  def walk(n: Int) = {
    val multiplier: Long = 0
    val modulus: Long = 0
    var seed: Int = 0
    var next: Int = 0

    next = seed
    next = ((multiplier * next) % modulus).asInstanceOf[Int]

  }

  println("Done")
}