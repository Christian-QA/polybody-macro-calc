package helpers

import play.api.mvc.QueryStringBindable

object MaleOrFemale {

  implicit def queryStringBindable(implicit
      intBinder: QueryStringBindable[String]
  ) =
    new QueryStringBindable[MaleOrFemale] {
      override def bind(
          key: String,
          params: Map[String, Seq[String]]
      ): Option[Either[String, MaleOrFemale]] = {
        for {
          sex <- intBinder.bind("sex", params)
        } yield {
          (sex) match {
            case (Right(sex)) => Right(MaleOrFemale(sex))
            case _            => Left("Unable to bind an sex")
          }
        }
      }
      override def unbind(key: String, sex: MaleOrFemale): String = {
        intBinder.unbind("sex", sex.toString)
      }
    }

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
