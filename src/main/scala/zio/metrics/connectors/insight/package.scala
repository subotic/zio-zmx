package zio.metrics.connectors

import zio._
import zio.metrics.connectors.internal.MetricsClient

package object insight {

  /* The insights layer that goes into the app that you would like to monitor */
  lazy val insightLayer: ZLayer[InsightsConfig & MetricsConfig, Nothing, Unit] =
    ZLayer.scoped(
      InsightServer.make.flatMap(clt => MetricsClient.make(insightHandler(clt))).unit,
    )

  private def insightHandler(clt: InsightServer): Iterable[MetricEvent] => UIO[Unit] = events => ???
}
