/*
 * Copyright 2022 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.softwarechoices

import io.gatling.core.Predef._
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.softwarechoices.SoftwareResultsRequests.{navigateToSoftwareResults, submitSoftwareResults}

import scala.concurrent.duration.DurationInt

object ProductDetailsRequests extends BaseRequests {

  def productDetailsUrl(productId: Int): String = s"$baseUrl/product-details?productId=$productId"
  val pauseTest: ActionBuilder = pause(25.seconds, 30.seconds).actionBuilders.last

  lazy val vendor5: Int = 105

  val navigateToProductDetails: HttpRequestBuilder =
    http("Navigate to product details page")
      .get(productDetailsUrl(vendor5))
      .check(status.is(200))

  def repeatResultsAndProductDetails: List[ActionBuilder] =
    repeat(2) {
      exec(navigateToSoftwareResults)
        .exec(pauseTest)
        .exec(submitSoftwareResults)
        .exec(navigateToProductDetails)
        .exec(pauseTest)
    }.actionBuilders
}
