package com.bitbucket.fllsouto.purplegraph.core

import org.scalactic._
import TripleEquals._
import org.scalatest.FunSuite

class VertexSpec extends FunSuite {

	implicit val doubleEquality = TolerantNumerics.tolerantDoubleEquality(0.000001)
	
	test("An initialized Vertex should have score 0") {
		var v = new Vertex("A", 0)
		assert(v.score === 0.0)
	}

	test("An initialized Vertex with score 0.1 should have closeness 0.1") {
		var v = new Vertex("A", 0)
		v.score = 0.1
		assert(v.closeness === 0.1)
	}

	test("An initialized Vertex with score 0.1 should have fairness 10") {
		var v = new Vertex("A", 0)
		v.score = 0.1
		assert(v.fairness === 10.0)
	}

	test("Two initialized Vertex should have different labels and gid") {
		var initGid = 0
		var v1 = new Vertex("A", initGid)
		initGid += 1
		var v2 = new Vertex("b", initGid)
		assert(v1.label != v2.label)
		assert(v1.gid != v2.gid)
	}
}