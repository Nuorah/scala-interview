package com.particeep.test.async

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success
import scala.util.Failure
import scala.concurrent.duration.Duration
import scala.concurrent.Await

/**
 * You have 2 webservices, we want to compute the sum of the 2 webservice call.
 *
 * You need to write only the compute function.
 * For instance : compute(1) should return 1099 (1098 + 1)
 *
 * It's part of the exercise to handle error cases
 */
object AsyncBasic {


  //I changed from Int to Future[Int] because imo it is clearer if the async is
  // resolved when you need to use the value
  def compute(id: String): Future[Int] = {
    val future1 = Webservice1.call(id).map {
      case Some(value) => value
      case None => throw new Exception("Webservice1: no value")
    }
    val future2 = Webservice2.call(id).map{
      case Right(value) => value
      case Left(value) => throw new Exception("Webservice2: no value")
    }

    for {
      a <- future1
      b <- future2
    } yield (a + b)
  }
}

object Webservice1 {
  private[this] val result = Map(
    "1"  -> 1,
    "2"  -> 21,
    "5"  -> 4,
    "10" -> 1987
  )

  def call(id: String): Future[Option[Int]] = Future(result.get(id))
}

object Webservice2 {
  private[this] val result = Map(
    "1"  -> 1098,
    "3"  -> 218777,
    "9"  -> 434,
    "10" -> Int.MaxValue
  )

  def call(id: String): Future[Either[String, Int]] = Future {
    result.get(id) match {
      case Some(x) => Right(x)
      case None    => Left("No value")
    }
  }
}
