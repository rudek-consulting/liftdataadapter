package code.lib

import net.liftweb.json._
import net.liftweb.http.js.JsCmds._
import net.liftweb.http.{RoundTripInfo, S}
import net.liftweb.util.Helpers._
import net.liftweb.http.js.JE.JsRaw

/**
 * Created by arne on 23.01.14.
 */

trait EmberLiftDataAdapter {
  implicit val formats = net.liftweb.json.DefaultFormats

  /**
   * Standard return object. Any return object should inherit from ReturnObject
   */
  trait ReturnObject

  /**
   * Javascript object name for the Ember Data Adapter.
   * Example of usage:
   *
   * App = Ember.Application.create();
   * App.ApplicationAdapter = EmberLiftDataAdapter;
   *
   * Should be overridden, if you need more than one DataAdapter on one page.
   */
  val adapterName = "EmberLiftDataAdapter"

  /**
   * Skeleton functions for data access
   */
  def find(input:JValue): ReturnObject
  def createRecord(input:JValue) : ReturnObject
  def updateRecord(input:JValue) : ReturnObject
  def deleteRecord(input:JValue) : ReturnObject
  def findAll(input:JValue) : List[ReturnObject]
  def findQuery(input:JValue) : List[ReturnObject]

  /**
   * renders the javascript for this adapter with all the server-bindings
   */
  def render = {
    val serverFuncs = JsCrVar("liftDataService",
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
      )).toJsCmd
    val serverFuncs2 = S.session.get.buildRoundtrip(
        List[RoundTripInfo]
          (
              "find" -> find _,
              "createRecord" -> createRecord _,
              "updateRecord" -> updateRecord _,
              "deleteRecord" -> deleteRecord _,
              "findAll"  -> findAll _,
              "findQuery" -> findQuery _
            )
      ).toJsCmd

    Script(
      JsRaw(
        s"""
          | var ${adapterName} = DS.Adapter.extend({
          |   serverFuncs: ${serverFuncs2},
          |
          |   find: function(store, type, id) {
          |     return liftDataService.save({type: type.typeKey, id:id} );
          |   },
          |
          |   createRecord: function(store, type, record) {
          |     return liftDataService.createRecord({type: type.typeKey, record:record} );
          |   },
          |
          |   updateRecord: function(store, type, record) {
          |     return liftDataService.updateRecord({type: type.typeKey, record:record} );
          |   },
          |
          |   deleteRecord: function(store, type, record) {
          |     return liftDataService.deleteRecord({type: type.typeKey, record:record} );
          |   },
          |
          |   findAll: function(store, type, sinceToken) {
          |     return this.serverFuncs.findAll({type: type.typeKey, sinceToken: sinceToken} );
          |   },
          |
          |   findQuery: function(store, type, query) {
          |     return liftDataService.findQuery({type: type.typeKey, query:query} );
          |   }
          | })
        """.stripMargin)
    )
  }

}
