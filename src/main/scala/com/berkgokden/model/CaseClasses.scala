package com.berkgokden.model

case class User(name: String, age: Int, address: Map[String, String], roles: List[String])

case class MapAnyCaseClass(name: String, metadata: Map[String, Any])