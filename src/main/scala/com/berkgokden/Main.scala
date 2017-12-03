package com.berkgokden

import com.berkgokden.model.{MapAnyCaseClass, User}
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import utils.MarshallableImplicits._

import scala.io.Source


object Main extends App {

  case class Person(name: String, age: Int)
  case class Group(name: String, persons: Seq[Person], leader: Person)

  val jeroen = Person("Jeroen", 26)
  val martin = Person("Martin", 54)

  val originalGroup = Group("Scala ppl", Seq(jeroen,martin), martin)
  // originalGroup: Group = Group(Scala ppl,List(Person(Jeroen,26), Person(Martin,54)),Person(Martin,54))

  val groupJson = originalGroup.toJson
  println(groupJson)
  // groupJson: String = {"name":"Scala ppl","persons":[{"name":"Jeroen","age":26},{"name":"Martin","age":54}],"leader":{"name":"Martin","age":54}}

  val group = groupJson.fromJson[Group]
  // group: Group = Group(Scala ppl,List(Person(Jeroen,26), Person(Martin,54)),Person(Martin,54))


  val groupYaml = group.toYaml
  println(groupYaml)

  val groupFromYaml = groupYaml.fromYaml[Group]

  val resource = Source.fromResource("user.yaml").reader()

  val user = resource.fromYamlFile[User]

  println(user)

  println("Exception test:")
  try {
    val resourceWithErrors = Source.fromResource("userwitherrors.yaml").reader()
    val userWithErrors = resourceWithErrors.fromYamlFile[User]
    println(userWithErrors)
  } catch {
    case e: InvalidFormatException => println(e.getMessage)
  }

  val a = MapAnyCaseClass("a", Map("b" -> 3, "d" -> Map("da" -> 1, "db" -> "str1"), "c" -> "str2" , "testList" -> List("1","2","3")))

  val aYaml = a.toYaml
  println(aYaml)

  val aBack = aYaml.fromYaml[MapAnyCaseClass]

}
