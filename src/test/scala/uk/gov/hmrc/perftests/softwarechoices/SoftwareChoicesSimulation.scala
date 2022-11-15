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
import uk.gov.hmrc.perftests.softwarechoices.GlossaryAutoRequests.submitGlossaryAutoSearch
import uk.gov.hmrc.perftests.softwarechoices.GlossaryManualRequests.{navigateToGlossary, submitGlossarySearch}
import uk.gov.hmrc.perftests.softwarechoices.ProductDetailsRequests.navigateToProductDetails
import uk.gov.hmrc.perftests.softwarechoices.SoftwareChoicesAutoRequests.submitSoftwareChoicesAutoSearch
import uk.gov.hmrc.perftests.softwarechoices.SoftwareChoicesManualRequests._

class SoftwareChoicesSimulation extends PerformanceTestRunner with FeatureSwitches {

  setup("software-choices-manual", "User navigates to software choices home page and uses manual http submissions")
    .withRequests(
      navigateToSoftwareChoicesHome,
      submitSoftwareChoicesSearch
    )

  setup("software-choices-js", "User searches for terms or filters using js route of the software choices home page")
    .withRequests(
      navigateToSoftwareChoicesHome,
      submitSoftwareChoicesAutoSearch
    )

  setup("product-details", "User views product details")
    .withRequests(
      setSwitches(switches),
      navigateToProductDetails
    )

  setup("glossary-manual", "User navigates to the glossary page and uses manual http submissions")
    .withRequests(
      navigateToGlossary,
      submitGlossarySearch
    )

  setup("glossary-js", "User searches for terms using js route of the glossary page")
    .withRequests(
      navigateToGlossary,
      submitGlossaryAutoSearch
    )

  runSimulation()

}
