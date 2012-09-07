package allTags

import org.specs._
import dispatch._
import liftjson.Js._
import dispatch.Request.toHandlerVerbs
import net.liftweb.json._
import JsonParser._
import java.util.UUID
import allTags.web.AllTagsPlan

object PlanSpec extends Specification with unfiltered.spec.jetty.Served {
  
  import dispatch._
  
  def setup = { _.filter(new AllTagsPlan(MokeDatas)) }
  
  val http = new Http
  
  "allTags app rest" should {
    "resources" in {
  
      val status = http x (host/"resources" as_str) {
        case (code, _, _, _) => code
      } 
      
      status must_== 200
      import allTags.AllTagsFormats._
      val resources= http(host/"resources" ># {
        _.children.map{
          _.extract[Resource]
        }
      })
      
      resources must_== MokeDatas.resources
     
      
    }
  }
}

