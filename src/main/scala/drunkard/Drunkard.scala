
package main.scala.drunkard

import scala.collection.mutable.ArrayBuffer
import scala.math.{pow, sqrt, exp, Pi}

object Drunkard extends App {
  // val input = readLine("prompt> ")
  var a: ArrayBuffer[Array[String]] = ArrayBuffer[Array[String]]()
  Iterator.continually(Console.readLine).takeWhile(_ != null).foreach(line => a += line.split(" "))

  def pseudo(n: Int) = {
    val multiplier: Long = 0
    val modulus: Long = 0
    var seed: Int = 0
    var next: Int = 0

    next = seed
    next = ((multiplier * next) % modulus).asInstanceOf[Int]

  }

  def walk(m: Int): String = {
    return "a"
  }
  def histrogram(m: Int, n: Int, seed: Int): String = {
    return "a"
  }

 /* def N(p: Int, m: Int) =  {
    return exp(pow(-1*p, 2)/(2*m)) / sqrt(2*Pi*m)
  }


  def H(p: Int) = {

  }

  def printWalk = {
    println("-");
  }
}

/*def reverse(s: String) = {
  s.length match {
    case 0
  }
  1
  > 1  {
      swap head and tail

  }
}

// memorize Aho Hopcroft and ullman - Data Structures
//
*/
* */
}