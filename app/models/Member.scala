package models

import play.api.libs.functional.syntax._
import play.api.libs.json._

case class Member(id: String,
                  name: String,
                  emailAddress: String,
                  balance: BigDecimal)

object Member {
  implicit lazy val format: OFormat[Member] = Json.format
}