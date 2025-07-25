/*
 * Copyright 2025 HM Revenue & Customs
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
object OtherItemsRequests extends BaseRequests {

  val pageUri: String = "/other-items"
  val fullUrl: String = baseUrl + pageUri

  val navigateToOtherItems: HttpRequestBuilder =
    http("Navigate to the other items page")
      .get(fullUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  val submitOtherItems: HttpRequestBuilder =
    http("Submit the other items page")
      .post(fullUrl)
      .formParamSeq(
        Seq(
          "csrfToken"    -> "${csrfToken}",
          "otherItems[]" -> "payments-into-a-private-pension",
          "otherItems[]" -> "charitable-giving",
          "otherItems[]" -> "capital-gains-tax",
          "otherItems[]" -> "student-loans",
          "otherItems[]" -> "marriage-allowance",
          "otherItems[]" -> "voluntary-class-2-national-insurance",
          "otherItems[]" -> "high-income-child-benefit-charge"
        )
      )
      .check(status.is(303))
      .check(redirectionLocationIs(AccountingPeriodRequests.pageUri))

}
