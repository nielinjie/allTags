package allTags

import nielinjie.util.io.FileDB
import nielinjie.util.io.HasId
import nielinjie.util.io.Serializer
import java.io.File

object LocalData extends Datas {
  val path = new File("./datas")
  implicit val ri:HasId[Resource] = new HasId[Resource] {
    override def getId(r: Resource) = r.id
  }
  implicit val f:Serializer[Resource] = new JsonSerializer[Resource]
  val resourceDB = new FileDB[Resource](new File(path,"resource"))
  override def resources = {
    resourceDB.list
  }
}