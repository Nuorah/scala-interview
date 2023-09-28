package com.particeep.test.basic

import play.api.i18n.Lang

import org.scalatestplus.play.PlaySpec

class ComputeAverageTest extends PlaySpec {

	"ComputeAverage" should {
		"compute the average of a list" in {
			ComputeAverage.average(List(1d, 10d, 16d)) mustBe 9d
		}
	}

}