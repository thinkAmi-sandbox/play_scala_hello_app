package controllers

import com.typesafe.config.ConfigFactory
import play.api.mvc._

import javax.inject.Inject

class ConfigLoaderController @Inject()(val cc: ControllerComponents, val config: play.api.Configuration) extends AbstractController(cc) {
  def diConfig() = Action { implicit request: Request[AnyContent] =>
    // injectされた config を使うパターン
    Ok(views.html.di_config(config.underlying.getString("foo")))
  }

  def load() = Action { implicit request: Request[AnyContent] =>
    // Factoryでloadする
    val conf = ConfigFactory.load()
    val value = conf.getString("foo")
    Ok(views.html.load_config(value))
  }
}
