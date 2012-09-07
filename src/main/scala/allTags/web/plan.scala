package allTags.web

import unfiltered.request._
import unfiltered.response._
import QParams._
import net.liftweb.json._
import Extraction.decompose
import net.liftweb.json.JsonDSL._
import net.liftweb.json.JsonAST._
import java.util.UUID
import org.clapper.avsl.Logger
import allTags.Datas
import allTags.MokeDatas
import allTags.AllTagsFormats

class AllTagsPlan(val datas: Datas) extends unfiltered.filter.Plan {
  import AllTagsFormats._
  def intent = {
    case GET(Path("/resources")) => {
      JsonAsPlain(decompose(datas.resources())) ~> Ok
    }
  }
}

object Server {
  val logger = Logger(Server.getClass)
  def main(args: Array[String]) {
    unfiltered.jetty.Http(8080).filter(new AllTagsPlan(MokeDatas))
      .resources(new java.net.URL(getClass().getResource("/www/css"), "."))
      .run({ svr =>
        unfiltered.util.Browser.open("localhost:8080")
      }, { svr =>
        logger.info("shutting down server")
      })
  }
}

