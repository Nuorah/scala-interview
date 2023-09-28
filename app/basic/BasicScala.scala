package com.particeep.test.basic

/**
 * This is basic language questions so don't use external library or build in function
 */
object BasicScala {

  /**
   * Encode parameter in url format
   *
   * Example:
   *
   * input  : Map("sort_by" -> "name", "order_by" -> "asc", "user_id" -> "12")
   * output : "?sort_by=name&order_by=asc&user_id=12"
   *
   * input  : Map()
   * output : ""
   */
  def encodeParamsInUrl(params: Map[String, String]): String = {
    "?" + params.toList.map{ case (a, b) => a + "=" + b}.reduce((a, b) => a + "&" + b)
  }

  /**
   * Test if a String is an email
   */
  def isEmail(maybeEmail: String): Boolean = {
    """(\S+)@(\S+)$""".r.findFirstMatchIn(maybeEmail).isDefined
  }

  /**
   * Compute i ^ n
   *
   * Example:
   *
   * input : (i = 2, n = 3) we compute 2^3 = 2x2x2
   * output : 8
   *
   * input : (i = 99, n = 38997)
   * output : 1723793299 
   */

   //of course for that last output the int is several times overflowed, for more overhead but exact values
   //we can replace Int by BigInt, or write power to be generic with Numeric[T] maybe
  def power(i: Int, n: Int): Int = {
    (i,n) match {
      case (0, 0) => throw new Exception("Illegal")
      case (0, _) => 0
      case (_, 0) => 1
      case (_, 1) => i
      case (_, n) if n%2 == 0 => power(i, n/2) * power(i, n/2)
      case _ => power(i, n/2) * power(i, n/2) * i
    } 
  }

}
