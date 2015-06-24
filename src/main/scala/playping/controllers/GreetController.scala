package playping
package controllers

import play.api.mvc._

class GreetController(msg: String) extends Controller {
  def greet = Action(Ok(msg))
}
