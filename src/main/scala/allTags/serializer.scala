package allTags

import net.liftweb.json.Serialization
import net.liftweb.json.NoTypeHints
import net.liftweb.json.Serializer
import java.util.UUID
import net.liftweb.json.TypeInfo
import net.liftweb.json.Formats

import net.liftweb.json.JsonAST._
import net.liftweb.json.JsonDSL._
import net.liftweb.json.Printer._
import net.liftweb.json.Extraction

object AllTagsFormats {
  implicit val formats = Serialization.formats(NoTypeHints) + new UUIDSerializer
}

class UUIDSerializer extends Serializer[UUID] {
  private val Class = classOf[UUID]

  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), UUID] = {
    case (TypeInfo(Class, _), json) => json match {
      case JString(s) => UUID.fromString(s)
      case _ => throw new IllegalArgumentException("can not deserialized to uuid")
    }
  }

  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case x: UUID =>
      x.toString
  }
}

class JsonSerializer[T](implicit m: Manifest[T]) extends nielinjie.util.io.Serializer[T] {
  implicit val formats = AllTagsFormats.formats
  override def serialize(obj: T) = {
    Extraction.decompose(obj)(formats).toString
  }
  override def unSerialize(string: String): T = {
    Extraction.extract[T](string)(formats, m)
  }
}

