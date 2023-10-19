import sbt._

object Dependencies {
  object Versions {
    val scala212 = "2.12.16"
    val scala213 = "2.13.8"
    val scala3 = "3.1.3"

    val trace4cats = "0.14.6"

    val trace4catsAvro = "0.14.0"

    val fs2Kafka = "2.5.0"
    val kafka = "2.8.1"
    val log4cats = "2.4.0"
    val logback = "1.2.12"

    val kindProjector = "0.13.2"
    val betterMonadicFor = "0.3.1"
  }

  lazy val trace4catsAvro = "io.janstenpickle"    %% "trace4cats-avro"    % Versions.trace4catsAvro
  lazy val trace4catsCore = "io.janstenpickle"    %% "trace4cats-core"    % Versions.trace4cats
  lazy val trace4catsTestkit = "io.janstenpickle" %% "trace4cats-testkit" % Versions.trace4cats

  lazy val embeddedKafka = "io.github.embeddedkafka" %% "embedded-kafka"  % Versions.kafka
  lazy val fs2Kafka = "com.github.fd4s"              %% "fs2-kafka"       % Versions.fs2Kafka
  lazy val log4cats = "org.typelevel"                %% "log4cats-slf4j"  % Versions.log4cats
  lazy val logback = "ch.qos.logback"                 % "logback-classic" % Versions.logback

  lazy val kindProjector = ("org.typelevel" % "kind-projector"     % Versions.kindProjector).cross(CrossVersion.full)
  lazy val betterMonadicFor = "com.olegpy" %% "better-monadic-for" % Versions.betterMonadicFor
}
