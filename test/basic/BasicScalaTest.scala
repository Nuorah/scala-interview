package com.particeep.test.basic

import play.api.i18n.Lang

import org.scalatestplus.play.PlaySpec

class BasicScalaTest extends PlaySpec {

  "BasicScala" should {
    "encode params in url" in {
      BasicScala.encodeParamsInUrl(
        Map("sort_by" -> "name", "order_by" -> "asc", "user_id" -> "12")
      ) mustBe "?sort_by=name&order_by=asc&user_id=12"
    }


    "compute email" in {
      BasicScala.isEmail("jean@particeep.com") mustBe true
      BasicScala.isEmail("jean_particeep.com") mustBe false
    }

    "compute i^n" in {
      a[Exception] must be thrownBy {
        BasicScala.power(0, 0)
      } 
      BasicScala.power(0, 1000) mustBe 0
      BasicScala.power(3, 0) mustBe 1
      BasicScala.power(2, 3) mustBe 8
      BasicScala.power(99, 38997) mustBe 1723793299
    }
  }
}
