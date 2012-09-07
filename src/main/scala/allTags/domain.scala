package allTags

import java.util.UUID

case class Resource(id: String, tags: List[Tag], subject: String, text: String)
object Resource {
  def apply(id: String, tags: List[Tag], subject: String): Resource = apply(id, tags, subject, subject)
}
case class Tag(id: String, name: String, text: String)
object Tag{
  //TODO: id is everything?
  def apply(id:String):Tag=Tag(id,id,id)
}

trait Datas {
  def resources(): List[Resource]
}