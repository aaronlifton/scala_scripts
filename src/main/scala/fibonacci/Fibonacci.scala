package main.scala.fibonacci

object Fibonacci extends App {
  def fib(n: Int): Int = {
    println("fib(" + n + ")")
    var ret:Int = 0
    if (n == 0) ret = 0 else ret = fib_helper(n, 1, 0, 1)
    println("fib=" + ret)
    return ret
  }

  def fib_helper(n: Int, m: Int, fibmm: Int, fibm: Int): Int = {
    println("fib_helper(" + n + ", " + m + ", " + fibmm + ", " + fibm + ")")
    var ret:Int = 0
    if (m == n) ret = fibm else ret = fib_helper(n, m + 1, fibm, fibmm + fibm)
    println("fib_helper=" + ret)
    return ret
  }

  def fib2(n: Int) : Int = n match {
    case 0 | 1 => n
    case _ => fib2(n - 1) + fib2(n - 2)
  }

  Seq(1,2,3,4,5).foreach { n =>
    fib(n)
//    println(fib2(n))
//    assert(fib(n) == fib2(n))
  }
}