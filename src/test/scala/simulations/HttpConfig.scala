package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object HttpConfig {
  val httpConf = http.baseUrl("http://jsonplaceholder.typicode.com")
    .header("Accept", value="application/json")
    .header("content-type", value="application/json")
}
