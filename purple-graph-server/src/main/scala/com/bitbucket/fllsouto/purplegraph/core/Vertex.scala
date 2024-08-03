package com.bitbucket.fllsouto.purplegraph.core

class Vertex(val label: String, val gid: Int) {
  var score = 0.0

  def fairness: Double = {
    return 1/this.closeness
  }

  def closeness: Double = {
    return this.score.toFloat;
  }
}