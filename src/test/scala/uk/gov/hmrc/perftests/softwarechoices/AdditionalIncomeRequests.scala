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

object AdditionalIncomeRequests extends BaseRequests {

  val pageUri: String = "/which-additional-income-source"
  val fullUrl: String = baseUrl + pageUri

  val navigateToAdditionalIncome: HttpRequestBuilder =
    http("Navigate to the additional income page")
      .get(fullUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  val submitAdditionalIncome: HttpRequestBuilder =
    http("Submit the additional income page")
      .post(fullUrl)
      .formParamSeq(
        Seq(
          "csrfToken"          -> "${csrfToken}",
          "additionalIncome[]" -> "uk-interest",
          "additionalIncome[]" -> "construction-industry-scheme",
          "additionalIncome[]" -> "employment",
          "additionalIncome[]" -> "uk-dividends",
          "additionalIncome[]" -> "state-pension-income",
          "additionalIncome[]" -> "private-pension-income"
//          "additionalIncome[]" -> "foreign-dividends",
//          "additionalIncome[]" -> "foreign-interest"
        )
      )
      .check(status.is(303))
      .check(redirectionLocationIs(OtherItemsRequests.pageUri))

}
