package playping
package controllers

import play.api._
import play.api.mvc._

class PingController extends Controller {
  def ping = Action(Ok("pong"))
}
