package code.snippet

import code.lib.{EmberLiftDataAdapter}
import net.liftweb.json.JsonAST.JString
import net.liftweb.common.Loggable

/**
 * Created by arne on 23.01.14.
 */
class myDataAdapter extends EmberLiftDataAdapter with Loggable {

  case class Todo(id:Int, title: String, isCompleted: Boolean) extends ReturnObject

  override val adapterName = "MyAdapter"

  override def findAll(input: _root_.net.liftweb.json.JValue): List[ReturnObject] = {
    for {
      JString(sType) <- input \ "type"
    //JString(sinceToken) <- input \ "sinceToken"
    } {
      logger.info("type = " + sType) // + " sinceToken = " + sinceToken)
    }

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

  def find(input: _root_.net.liftweb.json.JValue): ReturnObject = null

  def createRecord(input: _root_.net.liftweb.json.JValue): ReturnObject = null

  def updateRecord(input: _root_.net.liftweb.json.JValue): ReturnObject = null

  def deleteRecord(input: _root_.net.liftweb.json.JValue): ReturnObject = null

  def findQuery(input: _root_.net.liftweb.json.JValue): List[ReturnObject] = List()
}
