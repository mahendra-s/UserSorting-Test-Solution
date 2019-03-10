package com.example.poco

case class User(id: Int, name: String, username: String, email: String, address: Address,
  phone: String, website: String, company: Company)

case class Address(street: String, suite: String, city: String, zipcode: String, geo: GeoLocation)

case class Company(name: String, catchPhrase: String, bs: String)

case class GeoLocation(lng: String, lat: String) {
  def distance(that: GeoLocation, unit: String = "M"): Double = {
    val latitude = this.lat.toDouble
    val longitude = this.lng.toDouble
    val latitude1 = that.lat.toDouble
    val longitude1 = that.lng.toDouble
    if ((latitude == latitude1) && (longitude == longitude1)) 0.0
    else {
      val theta = longitude - longitude1
      var dist = Math.sin(Math.toRadians(latitude)) * Math.sin(Math.toRadians(latitude1)) + Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(theta))
      dist = Math.toDegrees(Math.acos(dist)) * 60 * 1.1515
      if (unit eq "K") dist = dist * 1.609344
      else if (unit eq "N") dist = dist * 0.8684
      dist
    }
  }
}