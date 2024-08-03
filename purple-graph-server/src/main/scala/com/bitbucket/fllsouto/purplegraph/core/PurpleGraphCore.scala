package com.bitbucket.fllsouto.purplegraph.core

import scala.collection.immutable.HashMap
import scala.collection.immutable.Set
import scala.io.Source
import scala.math._

class PurpleGraphCore() {
  var ranking = Seq[(String, Vertex)]()

  def calculateFullRankCloseness(inputLines: List[String], fraudList: Set[String]): Unit = {
    val graph = new Graph(inputLines, fraudList)
    graph.calculateCloseness
    ranking = graph.getFullRankCloseness
  }

  def getFullRankCloseness(): Seq[(String, Vertex)] = {
    return ranking
  }
}