lazy val commonSettings = Seq(
  Compile / compile / javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
  libraryDependencies ++= {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, _)) =>
        Seq(compilerPlugin(Dependencies.kindProjector), compilerPlugin(Dependencies.betterMonadicFor))
      case _ => Seq.empty
    }
  },
  scalacOptions := {
    val opts = scalacOptions.value
    val wconf = "-Wconf:any:wv"
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, _)) => opts :+ wconf
      case _ => opts
    }
  },
  Test / fork := true,
  resolvers += Resolver.sonatypeRepo("releases"),
)

lazy val noPublishSettings =
  commonSettings ++ Seq(publish := {}, publishArtifact := false, publishTo := None, publish / skip := true)

lazy val publishSettings = commonSettings ++ Seq(
  publishMavenStyle := true,
  pomIncludeRepository := { _ =>
    false
  },
  Test / publishArtifact := false
)

lazy val root = (project in file("."))
  .settings(noPublishSettings)
  .settings(name := "Trace4Cats Avro Kafka")
  .aggregate(`avro-kafka-consumer`, `avro-kafka-exporter`)

lazy val `avro-kafka-consumer` =
  (project in file("modules/avro-kafka-consumer"))
    .settings(publishSettings)
    .settings(
      name := "trace4cats-avro-kafka-consumer",
      libraryDependencies ++= Seq(Dependencies.trace4catsAvro, Dependencies.fs2Kafka, Dependencies.log4cats),
      libraryDependencies ++= Seq(Dependencies.trace4catsTestkit, Dependencies.embeddedKafka, Dependencies.logback)
        .map(_ % Test)
    )

lazy val `avro-kafka-exporter` =
  (project in file("modules/avro-kafka-exporter"))
    .settings(publishSettings)
    .settings(
      name := "trace4cats-avro-kafka-exporter",
      libraryDependencies ++= Seq(
        Dependencies.trace4catsAvro,
        Dependencies.trace4catsExporterCommon,
        Dependencies.fs2Kafka,
        Dependencies.log4cats
      ),
      libraryDependencies ++= Seq(Dependencies.trace4catsTestkit, Dependencies.embeddedKafka, Dependencies.logback)
        .map(_ % Test)
    )
