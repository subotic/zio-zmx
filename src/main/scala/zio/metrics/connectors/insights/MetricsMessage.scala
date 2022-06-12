package zio.metrics.connectors.insights

import java.time.Instant

import zio.metrics.MetricKey
import zio.metrics.MetricState

/* Messages that can be sent by the client to the server */
sealed trait ClientMessage

object ClientMessage {
  def subscribe: ClientMessage = Subscribe

  case object Subscribe extends ClientMessage
}

/* Messages that the server can return to the client  */
sealed trait MetricsMessage {
  def key: MetricKey.Untyped
  def when: Instant
}

object MetricsMessage {
  final case class CounterChange(
    key: MetricKey.Counter,
    when: Instant,
    absValue: Double,
    delta: Double)
      extends MetricsMessage
  final case class GaugeChange(
    key: MetricKey.Gauge,
    when: Instant,
    value: Double,
    delta: Double)
      extends MetricsMessage
  final case class HistogramChange(key: MetricKey.Histogram, when: Instant, value: MetricState[MetricKey.Histogram])
      extends MetricsMessage
  final case class SummaryChange(key: MetricKey.Summary, when: Instant, value: MetricState[MetricKey.Summary])
      extends MetricsMessage
}
