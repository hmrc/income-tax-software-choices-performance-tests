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

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.softwarechoices.AccountingPeriodRequests._
import uk.gov.hmrc.perftests.softwarechoices.AdditionalIncomeRequests._
import uk.gov.hmrc.perftests.softwarechoices.BusinessIncomeRequests._
import uk.gov.hmrc.perftests.softwarechoices.CheckYourAnswersRequests._
import uk.gov.hmrc.perftests.softwarechoices.OtherItemsRequests._
import uk.gov.hmrc.perftests.softwarechoices.ProductDetailsRequests._
import uk.gov.hmrc.perftests.softwarechoices.SoftwareChoicesToolRequests._

class SoftwareChoicesSimulation extends PerformanceTestRunner {

  setup("software-choices-tool", "User navigates to software choices home page and uses manual http submissions")
    .withRequests(
      navigateToSoftwareChoicesHome,
      submitSoftwareChoicesSearch
    )

  setup("product-details", "User views product details")
    .withRequests(
      navigateToProductDetails
    )

  setup("full-journey", "User journey from pre tool questions through to product details")
    .withRequests(
      navigateToBusinessIncome,
      submitBusinessIncome,
      navigateToAdditionalIncome,
      submitAdditionalIncome,
      navigateToOtherItems,
      submitOtherItems,
      navigateToAccountingPeriod,
      submitAccountingPeriod,
      navigateToCheckYourAnswers,
      submitCheckYourAnswers,
      navigateToSoftwareChoicesHome,
      submitSoftwareChoicesSearch,
      navigateToProductDetails
    )

  runSimulation()

}
