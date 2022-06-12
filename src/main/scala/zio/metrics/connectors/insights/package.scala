package zio.metrics.connectors

import zio._
import zio.metrics.connectors.internal.MetricsClient

package object insights {

  /* The insights layer that goes into the app that you would like to monitor */
  lazy val insightsLayer: ZLayer[InsightsConfig & MetricsConfig, Nothing, Unit] =
    ZLayer.scoped(
      InsightsServer.make.flatMap(clt => MetricsClient.make(insightsHandler(clt))).unit,
    )

  private def insightsHandler(clt: InsightsServer): Iterable[MetricEvent] => UIO[Unit] = events => ???
}
