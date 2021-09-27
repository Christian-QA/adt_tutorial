package controllers

import connectors.WeatherConnector
import play.api.mvc._

import javax.inject._

case class HttpException(message: String) extends Exception(message)

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(weatherConnector: WeatherConnector, val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>

    val forecast = weatherConnector.getForecast()

    if(forecast.status == 200)
      Ok(views.html.index(forecast.body))
    else
      throw HttpException("Something went wrong :(")
  }
}
