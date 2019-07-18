package controllers

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Helpers.stubControllerComponents
import play.api.test.{FakeRequest, Injecting}
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._

import scala.concurrent.ExecutionContext

class MemberControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "MemberController GET" should {

    val id = "AB00001"

    "return Ok with Json" in {
      val controller = new MemberController(stubControllerComponents())(ec: ExecutionContext)
      val member = controller.getMember(id).apply(FakeRequest(GET, "/member/:id"))

      status(member) mustBe OK
    }
  }

}
