name:="ScalaProject"

version:="1.0"

scalaVersion:="2.9.1"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers ++= Seq(
  "Web plugin repo" at "http://siasia.github.com/maven2",
  Resolver.url("Typesafe repository", new java.net.URL("http://typesafe.artifactoryonline.com/typesafe/ivy-releases/"))(Resolver.defaultIvyPatterns)
)

resolvers += "Central Repo" at "http://repo1.maven.org/maven2"

resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

resolvers += "Scala Tools Releases" at "http://scala-tools.org/repo-releases"

resolvers += "Scala Tools Snapshots" at "http://scala-tools.org/repo-snapshots"

resolvers += "Akka io" at "http://akka.io/repository"

resolvers += "novus rels" at "http://repo.novus.com/releases"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies <+= sbtVersion(v => "com.github.siasia" %% "xsbt-web-plugin" % ("0.1.0-"+v))

libraryDependencies <<= (libraryDependencies, sbtVersion) { (deps, version) => 
  deps :+ ("com.typesafe.sbteclipse" %% "sbteclipse" % "1.3-RC1" extra("sbtversion" -> version))
}
