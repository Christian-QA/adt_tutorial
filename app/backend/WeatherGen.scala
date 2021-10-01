package backend

import connectors.HttpResponse
import play.api.http.Status.{BAD_REQUEST, INTERNAL_SERVER_ERROR, NOT_FOUND, OK, SERVICE_UNAVAILABLE}

import scala.util.Random

// You do not have to change this
class WeatherGen {
  private def pick[A](possibilities: Seq[A], random: Random): A = random.shuffle(possibilities).head

  def createForecast(): HttpResponse = {
    val possibleForecasts = Seq("Sunny", "Rainy", "Snowy", "Cloudy")
    val possibleStatuses = Seq(OK, OK, OK, OK, NOT_FOUND, BAD_REQUEST, INTERNAL_SERVER_ERROR, SERVICE_UNAVAILABLE)

    val random = new Random

    pick(possibleStatuses, random)

    HttpResponse(pick(possibleStatuses, random), pick(possibleForecasts, random))
  }
}
