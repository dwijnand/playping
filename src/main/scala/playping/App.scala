package playping

import _root_.controllers.Assets
import play.api.ApplicationLoader.Context
import play.api.{ ApplicationLoader, BuiltInComponentsFromContext }
import router.Routes

object App {
  class Loader extends ApplicationLoader {
    def load(context: Context) = new Components(context).application
  }

  class Components(context: Context) extends BuiltInComponentsFromContext(context) {
    lazy val assets = new Assets(httpErrorHandler)

    lazy val rootController = new controllers.RootController

    lazy val router = new Routes(httpErrorHandler, rootController, assets)
  }
}
