package com.bitbucket.fllsouto.purplegraph.core

import org.scalactic._
import TripleEquals._
import org.scalatest.FunSuite

class GraphSpec extends FunSuite {
  implicit val doubleEquality = TolerantNumerics.tolerantDoubleEquality(0.000001)
  
  test("Given an initialized Graph with the following input it should have: 0 edges, 0 vertex, 0 fraud vertex and size equals 0") {
    var inputLines = List[String]()
    var fraudList = Set[String]()
    var graph = new Graph(inputLines, fraudList)

    assert(graph.getVertexList.isEmpty)
    assert(graph.getGraphEdges.isEmpty)
    assert(graph.getFraudList.isEmpty)
    assert(graph.getSize == 0)
  }

  test("Given an initialized Graph with the following input it should have: 4 vertex, 3 edges, 0 fraud vertex and size 4") {
    var inputLines = List[String]("0 1", "0 2", "0 3")
    var fraudList = Set[String]()
    var graph = new Graph(inputLines, fraudList)

    assert(graph.getVertexList.size == 4)
    assert(graph.getGraphEdges.size == 3)
    assert(graph.getFraudList.isEmpty)
    assert(graph.getSize == 4)
  }
  test("Given an initialized Graph with the following input it should have: 4 vertex, 6 edges, 0 fraud vertex and size 4") {
    var inputLines = List[String]("A B", "A C", "A D", "B C", "B D", "C D")
    var fraudList = Set[String]()
    var graph = new Graph(inputLines, fraudList)

    assert(graph.getVertexList.size == 4)
    assert(graph.getGraphEdges.size == 6)
    assert(graph.getFraudList.isEmpty)
    assert(graph.getSize == 4)
  }

  test("Given an initialized Graph with the following input, it should have: 6 edges, that 3 are inverse edge, 3 vertex, 6 edges, 0 fraud vertex and size 3") {
    var inputLines = List[String]("A B", "A C", "A D", "D A", "D A", "C A", "B A")
    var fraudList = Set[String]()
    var graph = new Graph(inputLines, fraudList)

    assert(graph.getVertexList.size == 4)
    assert(graph.getGraphEdges.size == 6)
    assert(graph.getFraudList.isEmpty)
    assert(graph.getSize == 4)
  }

  test("Given an initialized Graph with the following input, it should have the following resultant fairness") {
    var inputLines = List[String](
      "carlos tania",
      "maria eduardo",
      "maria tania",
      "eduardo henrique",
      "tania henrique",
      "henrique mauricio",
      "henrique jessica",
      "mauricio amanda",
      "amanda carlos"
    )
    var fraudList = Set[String]()
    var graph = new Graph(inputLines, fraudList)
    graph.calculateCloseness

    assert(graph.getVertexList.apply("carlos").fairness === 14.0)
    assert(graph.getVertexList.apply("maria").fairness === 15.0)
    assert(graph.getVertexList.apply("eduardo").fairness === 14.0)
    assert(graph.getVertexList.apply("tania").fairness === 11.0)
    assert(graph.getVertexList.apply("henrique").fairness === 10.0)
    assert(graph.getVertexList.apply("mauricio").fairness === 13.0)
    assert(graph.getVertexList.apply("amanda").fairness === 15.0)
    assert(graph.getVertexList.apply("jessica").fairness === 16.0)
  }


  test("Given an initialized Graph with the following input, it should have the following resultant closeness") {
    var inputLines = List[String](
      "carlos tania",
      "maria eduardo",
      "maria tania",
      "eduardo henrique",
      "tania henrique",
      "henrique mauricio",
      "henrique jessica",
      "mauricio amanda",
      "amanda carlos"
    )
    var fraudList = Set[String]()
    var graph = new Graph(inputLines, fraudList)
    graph.calculateCloseness
    
    assert(graph.getVertexList.apply("carlos").closeness === 0.0714285746216774)
    assert(graph.getVertexList.apply("maria").closeness === 0.06666667014360428)
    assert(graph.getVertexList.apply("eduardo").closeness === 0.0714285746216774)
    assert(graph.getVertexList.apply("tania").closeness === 0.09090909361839294)
    assert(graph.getVertexList.apply("henrique").closeness === 0.10000000149011612)
    assert(graph.getVertexList.apply("mauricio").closeness === 0.07692307978868484)
    assert(graph.getVertexList.apply("amanda").closeness === 0.06666667014360428)
    assert(graph.getVertexList.apply("jessica").closeness === 0.0625)
  }

  test("Given an initialized Graph with the following input, it should have the following resultant rank") {
    var inputLines = List[String](
      "carlos tania",
      "maria eduardo",
      "maria tania",
      "eduardo henrique",
      "tania henrique",
      "henrique mauricio",
      "henrique jessica",
      "mauricio amanda",
      "amanda carlos"
    )
    var fraudList = Set[String]()
    var graph = new Graph(inputLines, fraudList)
    var resultRank = Seq[String](
      "henrique", 
      "tania", 
      "mauricio", 
      "eduardo", 
      "carlos", 
      "amanda", 
      "maria", 
      "jessica"
    )
    graph.calculateCloseness

    assert(graph.getFullRankCloseness.map(v => v._1).equals(resultRank))
  }

  test("Given an initialized Graph with the following input, with fraud vertex 'henrique', it should have the following resultant rank") {
    var inputLines = List[String](
      "carlos tania",
      "maria eduardo",
      "maria tania",
      "eduardo henrique",
      "tania henrique",
      "henrique mauricio",
      "henrique jessica",
      "mauricio amanda",
      "amanda carlos"
    )
    var fraudList = Set[String]("henrique")
    var graph = new Graph(inputLines, fraudList)
    var resultRank = Seq[String](
      "carlos",
      "amanda",
      "maria",
      "tania",
      "mauricio",
      "eduardo",
      "jessica",
      "henrique"
    )
    graph.calculateCloseness

    assert(graph.getFullRankCloseness.map(v => v._1).equals(resultRank))
  }

  test("Given an initialized Graph with the following input, with duplicated fraud vertex 'henrique', it should have the following resultant rank") {
    var inputLines = List[String](
      "carlos tania",
      "maria eduardo",
      "maria tania",
      "eduardo henrique",
      "tania henrique",
      "henrique mauricio",
      "henrique jessica",
      "mauricio amanda",
      "amanda carlos"
    )
    var fraudList = Set[String]("henrique", "henrique")
    var graph = new Graph(inputLines, fraudList)
    var resultRank = Seq[String](
      "carlos",
      "amanda",
      "maria",
      "tania",
      "mauricio",
      "eduardo",
      "jessica",
      "henrique"
    )
    graph.calculateCloseness

    assert(graph.getFullRankCloseness.map(v => v._1).equals(resultRank))
  }
}