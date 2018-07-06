package org.beatific.red.utils.relation

class RelationNode(id:String) {
  var children:List[String] = List()
  var parents:List[String] = List()
  val name:String = id
  
  var leaf :Boolean = true
  var root :Boolean = true
  
  def addChild(child:String) {
    children :+= child
    leaf = false
  }
  
  def addParent(parent :String) {
    parents :+= parent
    root = false
  }
}