package main.scala.test

import org.scalacheck.Prop._
import org.scalacheck.Gen

//import main.scala.nspacegen.NSpaceGen._
import scala.util.Random

object TestNSpaceGen extends App {
  val propCheckDims = forAll { (a1: Array[Int], a2: Array[Int]) => a1.size == a2.size }

  val genSemiRandArray = Gen.containerOf[Array, Array[Int]] {
    val x = Array.fill(3)(Random.nextInt(100))
    x.updated(0, Array(1,2,3))
    x
  }
  val genRandArray = Gen.containerOf[Array, Array[Int]](Array.fill(3)(Random.nextInt(100)))

  val propEquiv1dSemiRandArray = forAll (genSemiRandArray, genSemiRandArray){ (a1, a2) => a1.deep == a2.deep }
  val propEquiv1dRandArray = forAll (genRandArray, genRandArray){ (a1, a2) => a1.deep == a2.deep }

  propCheckDims.check
  propEquiv1dSemiRandArray.check
  propEquiv1dRandArray.check
}
