package backend

import play.api.http.Status._

import scala.util.Random

// You do not have to change this
class WeatherGen {
  private def pick[A](possibilities: Seq[A], random: Random): A = random.shuffle(possibilities).head

  def createForecast(): HttpResponse = {
    val possibleForecasts = Seq("Sunny", "Rainy", "Snowy", "Cloudy")
    val possibleStatuses = Seq(OK, OK, OK, OK, NOT_FOUND, BAD_REQUEST, INTERNAL_SERVER_ERROR, SERVICE_UNAVAILABLE)

    val random = new Random

    HttpResponse(pick(possibleStatuses, random), pick(possibleForecasts, random))
  }
}
