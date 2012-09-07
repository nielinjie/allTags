package allTags

import java.util.UUID

object MokeDatas extends Datas {
  implicit def us(id: UUID): String = id.toString
  override val resources = {
    val ta = Tag(UUID.randomUUID, "A", "TagA")
    val tb = Tag(UUID.randomUUID, "B", "TagB")
    val tc = Tag(UUID.randomUUID, "C", "C, a log tag")
    val td = Tag(UUID.randomUUID, "D", "d, 俺是一枚中文哦")
    val te = Tag(UUID.randomUUID, "E", "e, kakaka")

    List(
      Resource(UUID.randomUUID, List(
        ta, tb), "a resource"),
      Resource(UUID.randomUUID, List(
        ta, tc), "b resource"),
      Resource(UUID.randomUUID, List(
        tc, td, te), "c resource"),
      Resource(UUID.randomUUID, List(
        td, te), "d resource"))
  }
}