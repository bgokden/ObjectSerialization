package com.berkgokden.utils

import java.io.InputStreamReader

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory


object YamlUtil {

  val mapper = new ObjectMapper(new YAMLFactory()) with ScalaObjectMapper
  mapper.registerModule(DefaultScalaModule)
  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

  def toYaml(value: Map[Symbol, Any]): String = {
    toYaml(value map { case (k,v) => k.name -> v})
  }

  def toYaml(value: Any): String = {
    mapper.writeValueAsString(value)
  }

  def fromYaml[T](json: String)(implicit m : Manifest[T]): T = {
    mapper.readValue[T](json)
  }

  def fromYamlFile[T](inputStream: InputStreamReader)(implicit m : Manifest[T]): T = {
    mapper.readValue[T](inputStream)
  }
}

