package zio.metrics.connectors.insight

import zio._

final case class InsightConfig(
  host: String,
  port: Int)

object InsightConfig {
  private val default: InsightConfig =
    InsightConfig("localhost", 8089)

  val defaultLayer: ULayer[InsightConfig] = ZLayer.succeed(default)
}
