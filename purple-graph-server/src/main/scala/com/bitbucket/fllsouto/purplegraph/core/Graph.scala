package com.bitbucket.fllsouto.purplegraph.core

import scala.collection.immutable.HashMap
import scala.collection.immutable.Set
import scala.io.Source
import scala.math._

class Graph() {

  private var  vertexList = HashMap[String, Vertex]()
  private var  graphEdges = Vector[Tuple2[String, String]]()
  private var  fraudList = Set[String]()
  private var  size = 0

  def this(graphEdgesLines: List[String], fraudList: Set[String]) {
    this()
    this.fraudList = this.fraudList ++ fraudList
    graphEdgesLines.distinct.map{(edge: String) => addEdgeToGraph(edge)}
    this.graphEdges.
      map(_._1).
      ++(this.
        graphEdges.
        map(_._2)
      ).
      distinct.
      map{
        case (v: String) => addVertexToGraph(v)
      }
  }

  def getVertexList(): HashMap[String, Vertex] = {
    return this.vertexList
  }

  def getGraphEdges(): Vector[Tuple2[String, String]] = {
    return this.graphEdges
  }

  def getFraudList(): Set[String] = {
    return this.fraudList
  }

  def getSize(): Integer = {
    return this.size
  }

  def addEdgeToGraph(edge: String): Unit = {
    edge.split(" ") match { case Array(v1: String, v2: String) =>
      this.graphEdges = this.graphEdges :+ (v1, v2)
    }
  }

  def addVertexToGraph(label: String): Unit = {
    this.vertexList = this.vertexList.+((label, new Vertex(label, this.size)))
    this.size += 1
  }

  def getFullRankCloseness: Seq[(String, Vertex)] = {
    return this.vertexList.toSeq.sortWith(_._2.closeness > _._2.closeness)
  }

  def showScores: Unit = {
    this.vertexList.foreach{case (l: String, v: Vertex) => {
      println("-----------------------------")
      println("Label : " + l)
      println("Gid : " + v.gid)
      println("Fairness :" + v.fairness)
      println("Closeness :" + v.closeness)
      println("-----------------------------")
    }}
  }

  def calculateCloseness: Unit = {
    var adjacenseM = Array.fill(this.size, this.size)(this.size)

    def buildAdjacense: Unit = {
      this.graphEdges.foreach((edge: Tuple2[String, String]) => {
        adjacenseM(this.vertexList(edge._1).gid)(this.vertexList(edge._2).gid) = 1
        adjacenseM(this.vertexList(edge._2).gid)(this.vertexList(edge._1).gid) = 1
      })
    }

    def floydWarshall: Unit = {
      // var counterK = 0
      // var counterI = 0
      // var counterJ = 0

      for (k <- 0 until this.size) {
        // counterK += 1
        for (i <- 0 until this.size) {
          // counterI += 1
          for (j <- i + 1 until this.size) {
            // counterJ += 1
            adjacenseM(i)(j) = (adjacenseM(i)(j) min (adjacenseM(i)(k) + adjacenseM(k)(j)))
            adjacenseM(j)(i) = adjacenseM(i)(j)
          }
        }
      }
      for (k <- 0 until this.size)
        adjacenseM(k)(k) = 0

      // println("Total of interactions K : " + counterK)
      // println("Total of interactions I : " + counterI)
      // println("Total of interactions J : " + counterJ)
    }

    def calculateScoreAndFrauds: Unit = {
      this.vertexList.foreach{ case (l: String, v: Vertex) => v.score = (1/adjacenseM(v.gid).reduceLeft(_ + _).toFloat)}
      
      var fraudVector = this.fraudList.map{ (vertexLabel: String) =>
        (vertexLabel, adjacenseM(this.vertexList(vertexLabel).gid).map{ (distance: Int) =>
          (1 - pow((1/2.0), distance))
        })
      }

      this.vertexList.foreach{ case (l: String, v: Vertex) =>
        fraudVector.foreach{ case (vertexLabel: String, fraudVector: Array[Double]) => 
          v.score = v.score * fraudVector(v.gid)
        }
      }
    }

    def showGraph: Unit = {
      for (vertex <- adjacenseM) {
        vertex.foreach((i: Int) => print(i + " "))
        print("\n")
      }
      print("\n")
    }

    buildAdjacense
    // showGraph
    floydWarshall
    // showGraph
    calculateScoreAndFrauds
    // showScores
  }
}