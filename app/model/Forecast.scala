package model

sealed trait Forecast
case object Sunny extends Forecast
case object Rainy extends Forecast
case object Snowy extends Forecast
case object Cloudy extends Forecast
case object Other extends Forecast