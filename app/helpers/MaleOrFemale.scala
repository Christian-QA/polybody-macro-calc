package helpers

object MaleOrFemale {
  def apply(value: String): MaleOrFemale =
    value match {
      case "male"   => Male
      case "female" => Female
      case "other"  => Other
    }
}

sealed trait MaleOrFemale

case object Male extends MaleOrFemale
case object Female extends MaleOrFemale
case object Other extends MaleOrFemale
