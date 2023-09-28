package com.particeep.test.basic

/**
 * What is the complexity of the function ?
 *
 * Refactor it to be a better function
 */
object Refactoring {

  case class File(
    name:     String,
    category: String
  )

  //before: O(n²) at worse because you traverse the list once and then again each time with contains
  //now: O(n) because you traverse the list once with map and then conversion to HashSet is O(n)

  def getCategories(files: List[File]): Set[String] = {
    files.map(_.category).toSet
  }
}
