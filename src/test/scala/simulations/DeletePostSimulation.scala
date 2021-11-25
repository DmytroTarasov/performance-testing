package simulations

import HttpConfig.httpConf
import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class DeletePostSimulation extends Simulation {

  def deletePostById(id: Int) = {
    exec(http("Delete specific post request")
      .delete(s"/posts/${id}")
      .check(status.in(200 to 204)))
  }
  val scn = scenario("Delete user scenario")
    .exec(deletePostById(2))

  setUp(scn.inject(
    rampUsers(50) during 10
  )).protocols(httpConf)
}