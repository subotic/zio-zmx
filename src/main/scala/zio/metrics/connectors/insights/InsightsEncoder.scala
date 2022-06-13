package zio.metrics.connectors.insights

import zio._
import zio.metrics.MetricKey
import zio.metrics.MetricState
import zio.metrics.connectors.MetricEvent

case object InsightsEncoder {

  def encode(event: MetricEvent): Task[MetricsMessage] =
    ZIO.attempt(encodeEvent(event))

  def encodeEvent(
    event: MetricEvent,
  ): MetricsMessage = {

    event.current match {
      case c: MetricState.Counter   => encodeCounter(c, event)
      case g: MetricState.Gauge     => encodeGauge(event.metricKey, g)
      case h: MetricState.Histogram => appendHistogram(result, event.metricKey, h)
      case s: MetricState.Summary   => appendSummary(result, event.metricKey, s)
      case f: MetricState.Frequency => appendFrequency(result, event.metricKey, f)
    }

    def encodeCounter(event: MetricEvent, c: MetricState.Counter): MetricsMessage = {

      val delta: Double = event match {
        case MetricEvent.New(_, current, _)          => current.asInstanceOf[MetricState.Counter].count
        case MetricEvent.Unchanged(_, _, _)          => 0.0d
        case MetricEvent.Updated(_, old, current, _) =>
          current.asInstanceOf[MetricState.Counter].count - old.asInstanceOf[MetricState.Counter].count
      }

      MetricsMessage.CounterChange(
        MetricKey.counter(event.metricKey.name),
        event.timestamp,
        c.count,
        delta,
      )
    }

    encodeGauge(event: MetricEvent, g: MetricState.Gauge): MetricsMessage = {
      val delta: Double = event match {
        case MetricEvent.New(_, current, _)          => current.asInstanceOf[MetricState.Counter].count
        case MetricEvent.Unchanged(_, _, _)          => 0.0d
        case MetricEvent.Updated(_, old, current, _) =>
          current.asInstanceOf[MetricState.Counter].count - old.asInstanceOf[MetricState.Counter].count
      }

      MetricsMessage.GaugeChange(
        
      )

    }

  }
}
