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

import io.gatling.core.Predef.pause
import io.gatling.core.action.builder.ActionBuilder
import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.softwarechoices.AccountingPeriodRequests._
import uk.gov.hmrc.perftests.softwarechoices.AdditionalIncomeRequests._
import uk.gov.hmrc.perftests.softwarechoices.BusinessIncomeRequests._
import uk.gov.hmrc.perftests.softwarechoices.CheckYourAnswersRequests._
import uk.gov.hmrc.perftests.softwarechoices.ChoosingSoftwareRequests.{navigateToChoosingSoftware, submitChoosingSoftware}
import uk.gov.hmrc.perftests.softwarechoices.IndexRequests._
import uk.gov.hmrc.perftests.softwarechoices.OtherItemsRequests._
import uk.gov.hmrc.perftests.softwarechoices.ProductDetailsRequests._
import uk.gov.hmrc.perftests.softwarechoices.UserTypeRequests._

import scala.concurrent.duration.DurationInt

class SoftwareChoicesSimulation extends PerformanceTestRunner {

  val pauseTest: ActionBuilder = pause(20.seconds).actionBuilders.last

  setup("agent-journey", "Agent uses the service through to product details")
    .withActions(
      navigateToUserType, pauseTest,
      submitUserType(isAgent = true)
    ).withActions(repeatResultsAndProductDetails:_*)

  setup("full-journey", "User journey from pre tool questions through to product details")
    .withActions(
      navigateToIndex,
      navigateToUserType, pauseTest,
      submitUserType(),
      navigateToBusinessIncome, pauseTest,
      submitBusinessIncome,
      navigateToAdditionalIncome, pauseTest,
      submitAdditionalIncome,
      navigateToOtherItems, pauseTest,
      submitOtherItems,
      navigateToAccountingPeriod, pauseTest,
      submitAccountingPeriod,
      navigateToCheckYourAnswers, pauseTest,
      submitCheckYourAnswers,
      navigateToChoosingSoftware, pauseTest,
      submitChoosingSoftware
    ) withActions (repeatResultsAndProductDetails:_*)


  runSimulation()

}
