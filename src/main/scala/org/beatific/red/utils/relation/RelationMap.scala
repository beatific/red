package org.beatific.red.utils.relation

import scala.annotation.tailrec

import scala.util.control.TailCalls._

object RelationMap {

  var map: Map[String, RelationNode] = Map()
  var roots: List[RelationNode] = List()

  def +(node: RelationNode) {

    map += (node.name -> node)

    node.root match {
      case true  => roots :+= node
      case false =>
    }
  }

  def flow(node: RelationNode): List[String] = {

    @tailrec
    def follow(list: List[String], i:Int): List[String] = {

      list(i) match {
        case null => list
        case child :String => follow(list ::: map(child).children, i+1)
      }
    }

    follow(List(node.name), 0)
  }
}

