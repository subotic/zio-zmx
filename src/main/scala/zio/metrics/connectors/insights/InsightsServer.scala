package zio.metrics.connectors.insights

import zio._

final case class InsightsServer(config: InsightsConfig)

object InsightsServer {
  def make: ZIO[Scope & InsightsConfig, Nothing, InsightsServer] =
    for {
      config <- ZIO.service[InsightsConfig]
      server <- ZIO.succeed(InsightsServer(config))
    } yield server
}
