package zio.metrics.connectors.insights

import zio._

final case class InsightsConfig(
  host: String,
  port: Int)

object InsightsConfig {
  private val default: InsightsConfig =
    InsightsConfig("localhost", 8089)

  val defaultLayer: ULayer[InsightsConfig] = ZLayer.succeed(default)
}
