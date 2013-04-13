package main.scala.passwords

import scala.util.Random
import scala.collection.mutable.ListBuffer
import scala.collection.SortedMap

object Passwords extends App {
  //	At Hogwarts we love to roast tyrants and tea kettles.
  //	val input = readLine("prompt> ")

  def clean(pre: String): List[String] = {
    val wordList = pre.replaceAll("\\d","").replaceAll("\\t"," ").toLowerCase.split(" ").toList // remove literal numbers, need to replace newlines
    return wordList
  }

  var a = ListBuffer[List[String]]()
  Iterator.continually(Console.readLine).takeWhile(_ != "").foreach(a += clean(_))

  val special = SortedMap(
    "and"     ->	"&", "zero"  ->	"0",
    "or"      ->	"|", "one"   -> "1",
    "not"     ->  "!", "two"   -> "2",
    "equal"   ->  "=", "three" -> "3",
    "plus"    ->	"+", "four"  ->	"4",
    "minus"   ->	"-", "five"  -> "5",
    "times"   ->	"*", "six"   ->	"6",
    "slash"   ->	"/", "seven" -> "7",
    "dollar"  ->  "$", "eight" -> "8",
    "percent" ->  "%", "nine"  -> "9",
    "at"      ->  "@", "to"    ->	"2",
    "for"     ->  "4", "ate"   -> "8")

  // handle cases
  def handleSpecial(a: List[String]): String = {
    a.map { w =>
      if (special.keySet.contains(w)) special(w) else {
        var s = w.headOption.getOrElse("").toString
        val tail = w.tail.drop(w.length-2) // w.slice(w.length-1, w.length)
        if (tail.matches("\\p{Punct}")) s += tail // need to ignore periods
        s
      }
    }.mkString("")
  }

  println(a.map(handleSpecial(_)).mkString("\n"))
}
