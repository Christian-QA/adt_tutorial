package connectors

import backend.WeatherGen
import play.api.http.Status._

import javax.inject.Inject

case class HttpException(message: String, status: Int) extends Exception(message)
class NotFoundException(message: String) extends HttpException(message, NOT_FOUND)
class BadRequestException(message: String) extends HttpException(message, NOT_FOUND)
class Upstream5xxResponse(message: String) extends HttpException(message, NOT_FOUND)

case class HttpResponse(status: Int, body: String)

class WeatherConnector @Inject()(weatherGen: WeatherGen) {
  def getForecast(): HttpResponse = {

    val response = weatherGen.createForecast()

    response.status match {
      case status if status == 200 => response
      case status if status == 404 => throw new NotFoundException("Could not find a forecast")
      case status if status == 400 => throw new BadRequestException("Something bad happened")
      case status if status >= 500 => throw new Upstream5xxResponse("Someone's stolen your barometer")
    }
  }
}
