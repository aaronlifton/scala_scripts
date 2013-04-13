package main.scala.isa

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap

object Isa extends App {

  def clean(pre: String): (String, String, Boolean) = {
    if (pre == ".") return (".", ".", true)
    val sets = Set[(String, String, Boolean)]()
    val s = pre.split(" ")
    var t = s(s.length - 1)
    val tail = t.slice(t.length - 1, t.length)
    val q: Boolean = tail.matches("[?]")
    if (q) t = t.slice(0, t.length - 1)

    (s(0), t, q)
  }

  var a: ListBuffer[(String, String, Boolean)] = ListBuffer[(String, String, Boolean)]()
  Iterator.continually(Console.readLine).takeWhile(_ != "").foreach(a += clean(_))

  val questions = a.filter(_._3 == true)

  var results = HashMap[(String, String), String]()

  questions.foreach {
    q =>
      var res = ""
      a.filter(_._3).foreach {
        a =>
          if (q._1 == ".") println(q._1)
          else {
            if (a._1 == q._1 && a._2 == q._2) res = "true"
            else res = "unknown"
            results((a._1, a._2)) = res
          }
      }
  }

  results.map(println(_))

}