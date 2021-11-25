package simulations

import HttpConfig.httpConf
import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class CreatePostSimulation extends Simulation {

  def createPost(filePath: String) = {
    exec(http("Create post request")
      .post("/posts")
      .body(RawFileBody(filePath)).asJson
      .header("content-type", "application/json")
      .check(status is 201))
  }

  val scn = scenario("Create post scenario")
    .exec(createPost("./src/test/bodies/create_post.json"))

  setUp(scn.inject(
    rampUsers(50) during 10
  )).protocols(httpConf)
}