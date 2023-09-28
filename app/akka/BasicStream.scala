package com.particeep.test.akka

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.{ Done, NotUsed }
import scala.concurrent.Future

/**
 * Question about Akka Stream framework https://doc.akka.io/docs/akka/current/stream/index.html
 *
 * Complete the code (replace the ???)
 */
object BasicStream {

  
  //sbt "runMain com.particeep.test.akka.BasicStream"
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("actor_system")

    val numbers = 1 to 100

    val numberSource: Source[Int, NotUsed] = Source.fromIterator(() => numbers.iterator)

    //Only let pass even number through the flow
    val isEvenFlow: Flow[Int, Int, NotUsed] =  Flow[Int].filter(n => n % 2 == 0)

    //Create a Source of even numbers by combining the number Source with the even Flow
    val evenNumberSource: Source[Int, NotUsed] = numberSource.via(isEvenFlow)

    //A Sink that will write its input onto the console
    def consoleSink[T]: Sink[T, Future[Done]] = Sink.foreach[T](println)

    //Connect the Source with the Sink and run it
    evenNumberSource.runWith(consoleSink[Int])

    //Bonus fizzbuzz

    /*val fizzbuzzFlow: Flow[Int, String, NotUsed] = Flow[Int].map{
      case n if n%3 == 0 && n%5 == 0 => "fizzbuzz"
      case n if n%3 == 0 => "fizz"
      case n if n%5 == 0 => "buzz"
      case n => n.toString()
    }

    val fizzbuzzSource: Source[String, NotUsed] = numberSource.via(fizzbuzzFlow)


    fizzbuzzSource.runWith(consoleSink[String])*/
  }
}
