package zio.metrics.connectors.insights

import zio._
import zio.metrics.connectors.MetricEvent
import zio.metrics.MetricState

case object InsightsEncoder {

  def encode(event: MetricEvent): Task[MetricsMessage] =
    ZIO.attempt(encodeEvent(event))

  def encodeEvent(
    event: MetricEvent,
  ): MetricsMessage = {

    event.current match {
      case c: MetricState.Counter   => encodeCounter(c)
      case g: MetricState.Gauge     => encodeGauge(g)
      case h: MetricState.Histogram => appendHistogram(result, event.metricKey, h)
      case s: MetricState.Summary   => appendSummary(result, event.metricKey, s)
      case f: MetricState.Frequency => appendFrequency(result, event.metricKey, f)
    }

    def encodeCounter(event: MetricEven): MetricsMessage = ???

  }
}
