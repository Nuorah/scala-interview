package com.particeep.test.async

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration


import play.api.i18n.Lang

import org.scalatestplus.play.PlaySpec
import java.{util => ju}

class AsyncBasicTest extends PlaySpec {

  "AsyncBasic" should {
    "eventually compute sum from fake webservices" in {
      Await.result(AsyncBasic.compute("1"), Duration("1s")) mustBe 1099
      val exception = intercept[Exception] {
        Await.result(AsyncBasic.compute("2"), Duration("1s"))
      }
      exception.getMessage() mustBe "Webservice2: no value"
      val exception2 = intercept[Exception] {
        Await.result(AsyncBasic.compute("3"), Duration("1s"))
      }
      exception.getMessage() mustBe "Webservice1: no Value"
    }
  }
}

