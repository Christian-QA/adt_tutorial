package connectors

import backend.WeatherGen
import model.{Cloudy, Forecast, Other, Rainy, Snowy, Sunny}
import play.api.http.Status._

import javax.inject.Inject

case class HttpSuccess(forecast: Forecast)
case class HttpError(status: Int, message: String)

class WeatherConnector @Inject()(weatherGen: WeatherGen) {

  private def weatherParse(data: String): Forecast = {
    data match {
      case "Sunny" => Sunny
      case "Rainy" => Rainy
      case "Snowy" => Snowy
      case "Cloudy" => Cloudy
      case _ => Other //TODO - Remove when Backend team learns what ADT is
    }
  }

  def getForecast(): Either[HttpError, HttpSuccess] = {

    val response = weatherGen.createForecast()
    val forecast = weatherParse(response.body)

    response.status match {
      case status if status == 200 && forecast != Other => Right(HttpSuccess(weatherParse(response.body)))
      case status if status == 200 && forecast == Other => Left(HttpError(NOT_FOUND, "Non-existent weather type")) //TODO - Remove when Backend team learns what ADT is
      case status if status == 404 => Left(HttpError(NOT_FOUND, "Could not find a forecast"))
      case status if status == 400 => Left(HttpError(BAD_REQUEST, "Something bad happened"))
      case status if status >= 500 => Left(HttpError(INTERNAL_SERVER_ERROR, "Someone's stolen your barometer"))
    }
  }
}
