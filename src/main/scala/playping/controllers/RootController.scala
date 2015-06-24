package playping
package controllers

import play.api._
import play.api.mvc._

class RootController extends Controller {
  def ping     = Action(Ok("pong"))
  def datetime = Action(Ok(java.time.ZonedDateTime.now()))
}
