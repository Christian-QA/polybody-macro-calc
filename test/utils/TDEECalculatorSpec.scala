package utils

import helpers.Male

import java.time.LocalDate

class TDEECalculatorSpec extends BaseSpec {

  val calculator: TDEECalculator = inject[TDEECalculator]

  "TDEECalculator" when {
    "bmrKatchMcArdleCalculator is called" must {
      "return 2206 calories for someone weighing 100kg with a body fat percent of 15" in {
        calculator.bmrKatchMcArdleCalculator(100, 15) mustBe 2206
      }
      "return 1990 calories for someone weighing 100kg with a body fat percent of 25" in {
        calculator.bmrKatchMcArdleCalculator(100, 25) mustBe 1990
      }
      "return 1633 calories for someone weighing 75kg with a body fat percent of 22" in {
        calculator.bmrKatchMcArdleCalculator(75, 22) mustBe 1633
      }
      "return 2340 calories for someone weighing 152kg with a body fat percent of 40" in {
        calculator.bmrKatchMcArdleCalculator(152, 40) mustBe 2339
      }
      "return 1661 calories for someone weighing 65kg with a body fat percent of 8" in {
        calculator.bmrKatchMcArdleCalculator(65, 8) mustBe 1661
      }
      "return 1428 calories for someone weighing 70kg with a body fat percent of 30" in {
        calculator.bmrKatchMcArdleCalculator(70, 30) mustBe 1428
      }
      "return 2073 calories for someone weighing 95kg with a body fat percent of 17" in {
        calculator.bmrKatchMcArdleCalculator(95, 17) mustBe 2073
      }
      "return 2196 calories for someone weighing 95kg with a body fat percent of 11" in {
        calculator.bmrKatchMcArdleCalculator(95, 11) mustBe 2196
      }
      "return 1966 calories for someone weighing 77kg with a body fat percent of 4" in {
        calculator.bmrKatchMcArdleCalculator(77, 4) mustBe 1966
      }
      "return 1405 calories for someone weighing 51kg with a body fat percent of 6" in {
        calculator.bmrKatchMcArdleCalculator(51, 6) mustBe 1405
      }
      "return 1588 calories for someone weighing 62kg with a body fat percent of 9" in {
        calculator.bmrKatchMcArdleCalculator(62, 9) mustBe 1588
      }
      "return 2702 calories for someone weighing 118kg with a body fat percent of 8.5" in {
        calculator.bmrKatchMcArdleCalculator(118, 8.5) mustBe 2702
      }
      "return 2048 calories for someone weighing 88kg with a body fat percent of 11.7" in {
        calculator.bmrKatchMcArdleCalculator(88, 11.7) mustBe 2048
      }
      "return 2021 calories for someone weighing 92kg with a body fat percent of 16.9" in {
        calculator.bmrKatchMcArdleCalculator(92, 16.9) mustBe 2021
      }
      "return 1679 calories for someone weighing 77kg with a body fat percent of 21.3" in {
        calculator.bmrKatchMcArdleCalculator(77, 21.3) mustBe 1678
      }
      "return 1796 calories for someone weighing 101.3kg with a body fat percent of 34.8" in {
        calculator.bmrKatchMcArdleCalculator(101.3, 34.8) mustBe 1796
      }
      "return 1997 calories for someone weighing 87.8kg with a body fat percent of14.2" in {
        calculator.bmrKatchMcArdleCalculator(87.8, 14.2) mustBe 1997
      }
      "return 2091 calories for someone weighing 96.5kg with a body fat percent of 17.4" in {
        calculator.bmrKatchMcArdleCalculator(96.5, 17.4) mustBe 2091
      }
      "return 1306 calories for someone weighing 54.9kg with a body fat percent of 21" in {
        calculator.bmrKatchMcArdleCalculator(54.9, 21) mustBe 1306
      }
      "return 1884 calories for someone weighing 94.75kg with a body fat percent of 26" in {
        calculator.bmrKatchMcArdleCalculator(94.75, 26) mustBe 1884
      }
    }

    "bmrMifflinStJeorCalculator is called" must {
      "return 1967 calories for a 190cm 25 year old male weighing 90kg" in {
        calculator.bmrMifflinStJeorCalculator(
          90,
          190,
          LocalDate.now.minusYears(25),
          Male
        ) mustBe 1967
      }
      "return 1580 calories for a 160cm 19 year old male weighing 67kg" in {
        calculator.bmrMifflinStJeorCalculator(
          67,
          160,
          LocalDate.now.minusYears(19),
          Male
        ) mustBe 1580
      }
      "return 2185 calories for a 200cm 34 year old male weighing 110kg" in {
        calculator.bmrMifflinStJeorCalculator(
          110,
          200,
          LocalDate.now.minusYears(34),
          Male
        ) mustBe 2185
      }
      "return 1615 calories for a 172cm 55 year old male weighing 81kg" in {
        calculator.bmrMifflinStJeorCalculator(
          81,
          172,
          LocalDate.now.minusYears(55),
          Male
        ) mustBe 1615
      }
      "return 1731 calories for a 177.2cm 28 year old male weighing 75.8kg" in {
        calculator.bmrMifflinStJeorCalculator(
          75.8,
          177.2,
          LocalDate.now.minusYears(28),
          Male
        ) mustBe 1730
      }
      "return 2016 calories for a 190.5cm 26 year old male weighing 95kg" in {
        calculator.bmrMifflinStJeorCalculator(
          95,
          190.5,
          LocalDate.now.minusYears(26),
          Male
        ) mustBe 2015
      }
      "return 1799 calories for a 182.25cm 31 year old male weighing 81kg" in {
        calculator.bmrMifflinStJeorCalculator(
          81,
          182.25,
          LocalDate.now.minusYears(31),
          Male
        ) mustBe 1799
      }
      "return 1121 calories for a 145cm 62 year old male weighing 52kg" in {
        calculator.bmrMifflinStJeorCalculator(
          52,
          145,
          LocalDate.now.minusYears(62),
          Male
        ) mustBe 1121
      }
      "return 954 calories for a 115cm 48 year old male weighing 47kg" in {
        calculator.bmrMifflinStJeorCalculator(
          47,
          115,
          LocalDate.now.minusYears(48),
          Male
        ) mustBe 953
      }
      "return 1690 calories for a 177.43cm 38 year old male weighing 76.58kg" in {
        calculator.bmrMifflinStJeorCalculator(
          76.58,
          177.43,
          LocalDate.now.minusYears(38),
          Male
        ) mustBe 1689
      }
    }
  }

}
