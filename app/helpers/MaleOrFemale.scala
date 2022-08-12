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
            case _            => Left("Unable to bind a sex")
          }
        }
      }
      override def unbind(key: String, sex: MaleOrFemale): String = {
        intBinder.unbind("sex", sex.toString)
      }
    }

  def apply(value: String): MaleOrFemale =
    value match {
      case "Male"     => Male
      case "Female"   => Female
      case "Intersex" => Intersex
    }
}

sealed trait MaleOrFemale

case object Male extends MaleOrFemale
case object Female extends MaleOrFemale
case object Intersex extends MaleOrFemale
