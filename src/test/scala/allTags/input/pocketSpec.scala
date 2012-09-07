import org.specs2.mutable._
import allTags.input.PocketInputer
import org.clapper.avsl.Logger
import nielinjie.util.io.FileUtil
import java.io.File
import net.liftweb.json.JsonParser
import net.liftweb.json.JsonAST.JObject

object PocketSpec extends Specification {
  def testData(fileName: String) = {
    new File(new java.net.URL(getClass().getResource("/testData"), "testData/" + fileName).getPath)
  }
  val log = Logger(classOf[App])
  "pocket input" in {
    "just parse it" in {
      val input = new PocketInputer
      val path = testData("pocket.json")
      val resources = input.parseResponse(FileUtil.fromFile(path))
      resources.toOption must beSome.which (_.size > 0 must beTrue)
    }
    "typic porket" in {
      val input = new PocketInputer
      val jo = JsonParser.parse(FileUtil.fromFile(testData("typicPocket.json"))).asInstanceOf[JObject]
      val resource = input.parseResource(jo)
      resource.subject must not beNull;
      resource.id must beEqualTo("49586127")
      resource.tags must not beEmpty
    }
  }
}