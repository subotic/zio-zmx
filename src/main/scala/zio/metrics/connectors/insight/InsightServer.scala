package zio.metrics.connectors.insight

import zio._

final case class InsightServer(config: InsightConfig)


/**
  * 13.7.2022: Questions
  * - MetricEvent structure
  * - MetricsMessage uses upickle. Should we stick to it or use ZIO-Json / ZIO-Schema
  * 
  */
object InsightServer {
  def make: ZIO[Scope & InsightConfig, Nothing, InsightServer] =
    for {
      config <- ZIO.service[InsightConfig]
      server <- ZIO.succeed(InsightServer(config))
    } yield server
}
