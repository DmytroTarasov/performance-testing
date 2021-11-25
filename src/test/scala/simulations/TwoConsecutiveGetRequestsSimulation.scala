package simulations

import HttpConfig.httpConf
import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class TwoConsecutiveGetRequestsSimulation extends Simulation {

  def getAllPosts() = {
    exec(http("Get all posts request")
      .get("/posts")
      .check(jsonPath("$[0].id").saveAs("postId")))
  }

  def getPostById() = {
    exec(http("Get specific post request")
      .get("/posts/${postId}")
      .check(jsonPath("$.id").is("1"))
      .check(jsonPath("$.userId").is("1")))
  }

  val scn = scenario("Check correlation and extract data scenario")
    .exec(getAllPosts())
    .pause(2)
    .exec(getPostById())

  setUp(scn.inject(
    rampUsers(50) during 10
  )).protocols(httpConf)
}
