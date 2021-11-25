package simulations

import HttpConfig.httpConf
import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class UpdatePostSimulation extends Simulation {

  def updatePost(id: Int, filePath: String) = {
    exec(http("Update specific post request")
      .put(s"/posts/${id}")
      .body(RawFileBody(filePath)).asJson
      .check(status.in(200 to 210)))
  }

  val scn = scenario("Update post scenario")
    .exec(updatePost(2, "./src/test/bodies/update_post.json"))

  setUp(scn.inject(
    rampUsers(50) during 10
  )).protocols(httpConf)
}