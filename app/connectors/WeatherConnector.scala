package connectors


import play.api.http.Status._

import scala.util.Random

case class HttpResponse(status: Int, body: String)

class WeatherConnector {

  private def pick[A](possibilities: Seq[A], random: Random): A = {
    possibilities(
      random.nextInt(possibilities.length)
    )
  }

  def getForecast(): HttpResponse = {
    val possibleForecasts = Seq("Sunny", "Rainy", "Snowy", "Cloudy")
    val possibleStatuses = Seq(OK, OK, OK, NOT_FOUND, INTERNAL_SERVER_ERROR, SERVICE_UNAVAILABLE)

    val random = new Random

    val forecast = pick(possibleForecasts, random)
    val status = pick(possibleStatuses, random)

    HttpResponse(status, forecast)
  }
}
