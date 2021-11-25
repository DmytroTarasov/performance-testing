package simulations

import HttpConfig.httpConf
import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class GetPostSimulation extends Simulation {

  def getPostById(id: Int) = {
    exec(http("Get specific post request")
      .get(s"/posts/${id}")
      .check(status is 200))
  }

  val scn = scenario("Get post scenario")
    .exec(getPostById(2))

  setUp(scn.inject(
    rampUsers(50) during 10
  )).protocols(httpConf)
}
