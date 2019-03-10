package com.example.util

import com.example.poco.GeoLocation
import com.typesafe.config.{ Config, ConfigFactory }

object Constants {
  private lazy val config: Config = ConfigFactory.load()
  val COLUMN_SEPARATOR = config.getString("output.separator.column")
  val LINE_SEPARATOR = config.getString("output.separator.line")
  val URL = config.getString("input.url")
  val OUTFILE = config.getString("outfile.name")

  val geoPoint = GeoLocation(
    config.getString("geo.point.longitude"),
    config.getString("geo.point.latitude"))
}
