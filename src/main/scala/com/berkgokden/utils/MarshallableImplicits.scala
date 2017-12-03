package com.berkgokden.utils

import java.io.InputStreamReader


object MarshallableImplicits {

  implicit class Unmarshallable(unMarshallMe: String) {
    def fromJson[T]()(implicit m: Manifest[T]): T =  JsonUtil.fromJson[T](unMarshallMe)
    def fromYaml[T]()(implicit m: Manifest[T]): T =  YamlUtil.fromYaml[T](unMarshallMe)
  }

  implicit class Marshallable[T](marshallMe: T) {
    def toJson: String = JsonUtil.toJson(marshallMe)
    def toYaml: String = YamlUtil.toYaml(marshallMe)
  }

  implicit class UnmarshallableStream(unMarshallMe: InputStreamReader) {
    def fromYamlFile[T]()(implicit m: Manifest[T]): T =  YamlUtil.fromYamlFile[T](unMarshallMe)
  }
}