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
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

trait FeatureSwitches extends BaseRequests {
  private val featureSwitchUrl: String = baseUrl + "/test-only/feature-switch"

  val switches: Map[String, String] = Map(
    "feature-switch" -> "enable-beta-features"
  )

  def setSwitches(switches: Map[String, String]): HttpRequestBuilder  = {
    http("Set feature switch")
      .post(featureSwitchUrl)
      .formParamMap(switches)
      .check(status.is(303))
      .silent
  }
}
