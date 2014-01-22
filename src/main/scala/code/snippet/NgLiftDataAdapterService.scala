package code.snippet

import net.liftweb.common.{Full, Loggable}
import net.liftweb.json._
import net.liftweb.http.js.JsCmds._
import net.liftweb.http.{RoundTripInfo, S}
import net.liftweb.http.js.JE._
import net.liftweb.http.js.JE.Call
import net.liftweb.http.js.JE.JsVar
import net.liftweb.http.js.JsCmds.JsCrVar
//import net.liftweb.json.JsonAST.{render => r}
//import net.liftweb.http.js.JsCmds

//, JString}

//import net.liftweb.json.Extraction.decompose
import net.liftweb.json.Printer.{pretty => p}
/**
 * Created by arne on 19.01.14.
 */
class NgLiftDataAdapterService extends Loggable {

  implicit val formats = net.liftweb.json.DefaultFormats
  case class Todo(id:Int, title: String, isCompleted: Boolean)

  def find(input:JValue): JValue = {
    logger.info ("find(): " + input)
    JNull
  }

  def createRecord(input:JValue) : JValue = {
    logger.info ("createRecord(): " + input)
    JNull
  }

  def updateRecord(input:JValue) : JValue = {
    logger.info ("updateRecord(): " + input)
    JNull
  }

  def deleteRecord(input:JValue) : JValue = {
    logger.info ("deleteRecord(): " + input)
    JNull
  }

  def findAll(input:JValue) : List[Todo] = {
    val elems = List(
      Todo(0,"Erstes Todo", false),
      Todo(1,"ZweitesTodo", true),
      Todo(2,"huhu", true),
      Todo(3,"Huibu", true),
      Todo(4,"Dingsda", true),
      Todo(5,"Blupp", true)
    )

    elems
  }

  def findQuery(input:JValue) : JValue = {
    logger.info ("findQuery(): " + input)
    JNull
  }

  def render = {
    Script(
      JsCrVar("serverFuncs",
        S.session.get.buildRoundtrip(
          List[RoundTripInfo]
            (
                "find" -> find _,
                "createRecord" -> createRecord _,
                "updateRecord" -> updateRecord _,
                "deleteRecord" -> deleteRecord _,
                "findAll"  -> findAll _,
                "findQuery" -> findQuery _
              )
        )
      )
    )
  }

}
