import play.api.http.Writeable
import play.api.mvc.Codec

import java.time.ZonedDateTime

package object playping
  extends PlayImplicits {

}

package playping {
  trait PlayImplicits {
    implicit def zonedDateTimeWritable(implicit codec: Codec): Writeable[ZonedDateTime] =
      implicitly[Writeable[String]].map[ZonedDateTime](_.toString)
  }
}
