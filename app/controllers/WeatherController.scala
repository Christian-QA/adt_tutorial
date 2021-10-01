package controllers

import connectors.WeatherConnector
import play.api.mvc._

import javax.inject._

@Singleton
class HomeController @Inject()(weatherConnector: WeatherConnector, val controllerComponents: ControllerComponents) extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>

    val forecast = weatherConnector.getForecast()

    Ok(views.html.index(forecast.body))
  }
}
