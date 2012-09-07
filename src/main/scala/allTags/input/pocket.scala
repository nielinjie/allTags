package allTags.input

import allTags.Resource
import allTags.Json._
import dispatch.Http
import net.liftweb.json._
import JsonParser._
import dispatch._
import liftjson.Js._
import allTags.AllTagsFormats
import java.net.URL
import scala.io.Source
import org.clapper.avsl.Logger
import scalaz._
import Scalaz._
import nielinjie.util.data.LookUp._
import allTags.Tag

class PocketInputer {
  val apikey = "0G9A6c6dd54e9fcH34p3f2eZemT6xWo9"
  val username = "nielinjie"
  val password = "790127"
  val url_ = "https://readitlaterlist.com/v2/get?username=%s&password=%s&apikey=%s".format(username, password, apikey)
  val logger = Logger(this.getClass)
  implicit val formats = Serialization.formats(NoTypeHints).+(new PocketInputJsonFormater)

  def input(theUrl: URL): Validation[String, List[Resource]] = {
    val http = new Http()
    parseResponse(http(url(theUrl.toString) as_str))
  }
  
  def parseResponse(json: String): Validation[String, List[Resource]] = {
    val lookUpResource = for {
      a <- lookUpJson(_ \ "list").required.fmap(_.children.map {
        jv: JValue =>
          parseResource(jv.asInstanceOf[JField].value.asInstanceOf[JObject])
      })
    } yield (a)
    lookUpResource(JsonParser.parse(json))
  }
  def parseResource(json: JObject) = {
    json.extract[Resource]
  }

  class PocketInputJsonFormater extends JsonSerializer[Resource] {
    override val clazz = classOf[Resource]
    override val look = for {
      id <- lookUpJson(_ \ "item_id").required.as[JString].fmap(_.values);
      subject <- lookUpJson(_ \ "title").required.as[JString].fmap(_.values);
      tags <- lookUpJson(_ \ "tags").as[JString].fmap(_.values.split(",").toList.map{Tag(_)})
    } yield (Resource(id, tags.getOrElse(List()), subject))

  }

}