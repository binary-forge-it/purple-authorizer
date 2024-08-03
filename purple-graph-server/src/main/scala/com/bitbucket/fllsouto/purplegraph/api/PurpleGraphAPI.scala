package com.bitbucket.fllsouto.purplegraph.api

import com.bitbucket.fllsouto.purplegraph.core._
import org.scalatra._

import org.scalatra.json._
import org.json4s.{DefaultFormats, Formats}
import org.json4s.JsonDSL._

class PurpleGraphAPI extends ScalatraServlet with NativeJsonSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats
  private var purpleGraph = new PurpleGraphCore()
  private var inputVal = List[String]()
	private var fraudList = Set[String]()

  // Before every action runs, set the content type to be in JSON format.
  before() {
    contentType = formats("json")
  }

  get("/rank") {
    println("Receiving GET /rank ")
    response.addHeader("ACK", "GOT IT")
    purpleGraph.getFullRankCloseness.map(vertex => (("label" -> vertex._1) ~ ("score" -> vertex._2.closeness)))
  }

  post("/rank") {
    println("Receiving POST /new")
    val jsonString = request.body
    implicit val formats = DefaultFormats
    val jValue = parse(jsonString)
    inputVal = inputVal ++ (jValue \ "input").extract[List[String]]
    purpleGraph.calculateFullRankCloseness(inputVal, fraudList)
    response.addHeader("ACK", "GOT IT")
  }

  post("/rank/reset") {
    println("Receiving POST /rank/reset ")
    inputVal    = List[String]()
    fraudList   = Set[String]()
    purpleGraph = new PurpleGraphCore()
    response.addHeader("ACK", "GOT IT")
  }

  post("/fraud") {
    println("Receiving POST /fraud")
    val jsonString = request.body
    implicit val formats = DefaultFormats
    val jValue = parse(jsonString)
    fraudList = fraudList ++ (jValue \ "input").extract[Set[String]]
    purpleGraph.calculateFullRankCloseness(inputVal, fraudList)
    response.addHeader("ACK", "GOT IT")
  }
}