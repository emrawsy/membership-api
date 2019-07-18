package controllers

import javax.inject.Inject
import models.Member
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.play.json.collection.JSONCollection
import reactivemongo.play.json.ImplicitBSONHandlers.JsObjectDocumentWriter

import scala.concurrent.{ExecutionContext, Future}

class MemberController @Inject()(cc: ControllerComponents, mongo: ReactiveMongoApi)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  private def collection: Future[JSONCollection] =
    mongo.database.map(_.collection[JSONCollection]("members"))

  private def get(id: String): Future[Option[Member]] =
    collection.flatMap(_.find(
      Json.obj("id" -> id),
      None
    ).one[Member])

  def getMember(id: String) = Action.async {
    implicit request: Request[AnyContent] =>
      get(id).map(
        _.map {
          member =>
            Ok(Json.toJson(member))
        }.getOrElse(NotFound)
      )
  }

//  def postMember = Action.async(parse.json[Member]) {
//    implicit request =>
//      request.body
//      ???
//  }
}

