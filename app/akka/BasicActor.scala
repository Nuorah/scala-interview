package com.particeep.test.akka

import akka.actor.{ Actor, ActorSystem, Props }

/**
 * Question about Akka framework http://akka.io
 *
 * When receiving a message that says "Hello", BasicActor must print "Hello there."
 * It must print "What?" when receiving any other message
 */

object BasicActor {
  final case class Hello(message:String);

}
class BasicActor extends Actor {
  import BasicActor._

  override def receive: Receive = {
    case Hello("Hello") => println("Hello there.")
    case Hello(_) => println("What?")
  }

}

//sbt "runMain com.particeep.test.akka.FireActor"
object FireActor {

  import BasicActor._
  /**
   * Create an instance of BasicActor
   *
   * Make it print "Hello there." and "What?"
   */
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("actor_system")
    val basic_actor = system.actorOf(Props[BasicActor], name = "basic_actor")
    basic_actor ! Hello("Hello")
    basic_actor ! Hello("Sunny day innit?") 
  }
}
