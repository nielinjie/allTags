package allTags

import net.liftweb.json._
import JsonParser._
import org.clapper.avsl.Logger

import scalaz._
import Scalaz._

import nielinjie.util.data.LookUp._

object Json {
  def lookUpJson(f: JValue => JValue) = lookUpFor[JValue](f)
  implicit def jsonProjection: (JValue, JValue => JValue) => Option[JValue] = {
    (x, f) =>
      f(x) match {
        case JNull => None
        case JNothing => None
        case json: JValue => Some(json)
        case _ => None
      }
  }
  abstract class JsonDeserializer[T] {
    val clazz: Class[T]
    val look: LookingUp[JValue => JValue, JValue, T, Option]
    def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), T] = {
      case (TypeInfo(clazz, _), jbj: JObject) => {
        look(jbj) ||| { x: String => throw new IllegalStateException(x) }
      }
    }
  }
  abstract class JsonSerializer[T] extends JsonDeserializer[T] with Serializer[T] {
    def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
      case _ =>
        throw new UnsupportedOperationException("don't go here")
    }
  }
}