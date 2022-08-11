package utils

class TDEECalculatorSpec extends BaseSpec {

  val calculator: TDEECalculator = inject[TDEECalculator]

  "TDEECalculator" when {
    "bmrKatchMcArdleCalculator is called" must {
      "return 2206 calories for someone weighing 100kg with a body fat percent of 15" in {
        calculator.bmrKatchMcArdleCalculator(100, 15) mustBe 2206
      }
      "return 1990 calories for someone weighing 100 with a body fat percent of 25" in {
        calculator.bmrKatchMcArdleCalculator(100, 25) mustBe 1990
      }
      "return 1633 calories for someone weighing 75 with a body fat percent of 22" in {
        calculator.bmrKatchMcArdleCalculator(75, 22) mustBe 1633
      }
      "return 2340 calories for someone weighing 152 with a body fat percent of 40" in {
        calculator.bmrKatchMcArdleCalculator(152, 40) mustBe 2339
      }
      "return 1661 calories for someone weighing 65 with a body fat percent of 8" in {
        calculator.bmrKatchMcArdleCalculator(65, 8) mustBe 1661
      }
      "return 1428 calories for someone weighing 70 with a body fat percent of 30" in {
        calculator.bmrKatchMcArdleCalculator(70, 30) mustBe 1428
      }
      "return 2073 calories for someone weighing 95 with a body fat percent of 17" in {
        calculator.bmrKatchMcArdleCalculator(95, 17) mustBe 2073
      }
      "return 2196 calories for someone weighing 95 with a body fat percent of 11" in {
        calculator.bmrKatchMcArdleCalculator(95, 11) mustBe 2196
      }
      "return 1966 calories for someone weighing 77 with a body fat percent of 4" in {
        calculator.bmrKatchMcArdleCalculator(77, 4) mustBe 1966
      }
      "return 1405 calories for someone weighing 51 with a body fat percent of 6" in {
        calculator.bmrKatchMcArdleCalculator(51, 6) mustBe 1405
      }
      "return 1588 calories for someone weighing 62 with a body fat percent of 9" in {
        calculator.bmrKatchMcArdleCalculator(62, 9) mustBe 1588
      }
      "return 1588 calories for someone weighing 118 with a body fat percent of 8.5" in {
        calculator.bmrKatchMcArdleCalculator(118, 8.5) mustBe 2702
      }
      "return 2048 calories for someone weighing 88 with a body fat percent of 11.7" in {
        calculator.bmrKatchMcArdleCalculator(88, 11.7) mustBe 2048
      }
      "return 2021 calories for someone weighing 92 with a body fat percent of 16.9" in {
        calculator.bmrKatchMcArdleCalculator(92, 16.9) mustBe 2021
      }
      "return 1679 calories for someone weighing 77 with a body fat percent of 21.3" in {
        calculator.bmrKatchMcArdleCalculator(77, 21.3) mustBe 1678
      }
      "return 1796 calories for someone weighing 101.3 with a body fat percent of 34.8" in {
        calculator.bmrKatchMcArdleCalculator(101.3, 34.8) mustBe 1796
      }
      "return 1997 calories for someone weighing 87.8 with a body fat percent of14.2" in {
        calculator.bmrKatchMcArdleCalculator(87.8, 14.2) mustBe 1997
      }
      "return 2091 calories for someone weighing 96.5 with a body fat percent of 17.4" in {
        calculator.bmrKatchMcArdleCalculator(96.5, 17.4) mustBe 2091
      }
      "return 1306 calories for someone weighing 54.9 with a body fat percent of 21" in {
        calculator.bmrKatchMcArdleCalculator(54.9, 21) mustBe 1306
      }
      "return 1884 calories for someone weighing 94.75 with a body fat percent of 26" in {
        calculator.bmrKatchMcArdleCalculator(94.75, 26) mustBe 1884
      }
    }
  }

}
