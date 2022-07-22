package dto

import helpers.{Male, VeryActive}
import models.MacroStat
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{JsObject, JsResultException, Json}
import utils.BaseSpec

import java.time.LocalDate

class MacroCalcDtoSpec extends BaseSpec {

  val macroCalcDto: MacroCalcDto = new MacroCalcDto(
    Male,
    LocalDate.now.minusYears(20),
    150.5d,
    150,
    120,
    VeryActive,
    Some(20),
    None,
    Some(2500),
    Some(2000),
    Some(10)
  )

}
