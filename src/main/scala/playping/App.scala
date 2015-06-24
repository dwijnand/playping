package playping

import _root_.controllers.Assets
import play.api.ApplicationLoader.Context
import play.api.{ ApplicationLoader, BuiltInComponentsFromContext }
import router.Routes

class AppLoader extends ApplicationLoader {
  def load(context: Context) = new AppComponents(context).application
}

class AppComponents(context: Context) extends BuiltInComponentsFromContext(context) {
  lazy val assets = new Assets(httpErrorHandler)

  lazy val pingController = new controllers.PingController

  lazy val router = new Routes(httpErrorHandler, pingController, assets)
}
