organization := "pl.project13.scala"

name := "akka-persistence-hbase"

version := "0.4.2-SNAPSHOT"

scalaVersion := "2.11.0"

crossScalaVersions := Seq("2.10.4", "2.11.0")

parallelExecution in Test := false

// dependencies ---------------------------------------------------------------

resolvers += "cloudera cdh" at "https://repository.cloudera.com/artifactory/cloudera-repos"

resolvers += "akka snapshots" at "http://repo.akka.io/snapshots"

resolvers += "krasserm at bintray" at "http://dl.bintray.com/krasserm/maven"

val akkaVersion = "2.4.4"

val hadoopVersion = "2.6.0-cdh5.7.0"

val hbaseVersion = "1.2.0-cdh5.7.0"

libraryDependencies += "org.apache.hadoop" % "hadoop-common"   % hadoopVersion

libraryDependencies += "org.apache.hadoop" % "hadoop-client" % hadoopVersion

libraryDependencies += "org.apache.hbase"  % "hbase-common"  % hbaseVersion % "compile"

libraryDependencies += "org.apache.hbase"  % "hbase-client"  % hbaseVersion % "compile"

libraryDependencies += ("org.hbase"        % "asynchbase"    % "1.7.1")
  .exclude("org.slf4j", "log4j-over-slf4j")
  .exclude("org.slf4j", "jcl-over-slf4j")

libraryDependencies += "org.slf4j"            % "slf4j-log4j12"                 % "1.6.0"     % "provided"

libraryDependencies += "com.typesafe.akka"   %% "akka-persistence" % akkaVersion % "compile"

libraryDependencies += "com.typesafe.akka"   %% "akka-persistence-query-experimental" % akkaVersion % "compile"

libraryDependencies += "com.typesafe.akka"   %% "akka-testkit"                  % akkaVersion % "test"

libraryDependencies += "com.github.krasserm" %% "akka-persistence-testkit"      % "0.3.3"     % "test"

libraryDependencies += "org.scalatest"       %% "scalatest"                     % "2.2.0"     % "test"

// publishing settings --------------------------------------------------------

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

credentials += Credentials(Path.userHome / ".sbt" / "sonatype.properties")

pomExtra := (
<url>http://github.com/ktoso/akka-persistence-hbase</url>
<licenses>
  <license>
    <name>Apache 2 License</name>
    <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
    <distribution>repo</distribution>
  </license>
</licenses>
<scm>
  <url>git@github.com:ktoso/akka-persistence-hbase.git</url>
  <connection>scm:git:git@github.com:ktoso/akka-persistence-hbase.git</connection>
</scm>
<developers>
  <developer>
    <id>ktoso</id>
    <name>Konrad 'ktoso' Malawski</name>
    <url>http://blog.project13.pl</url>
  </developer>
</developers>
<parent>
  <groupId>org.sonatype.oss</groupId>
  <artifactId>oss-parent</artifactId>
  <version>7</version>
</parent>)
