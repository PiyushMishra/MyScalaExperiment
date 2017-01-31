sourceDirectories in (Compile, TwirlKeys.compileTemplates) := (unmanagedSourceDirectories in Compile).value

name:="code-gen"

libraryDependencies+="io.argonaut" %% "argonaut" % "6.1"     
 
libraryDependencies+="com.typesafe.play" % "play-json_2.11" % "2.5.10"
 
libraryDependencies+="io.spray" %%  "spray-json" % "1.3.3"
 
libraryDependencies+="org.scalariform" % "scalariform_2.11" % "0.1.8"

libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.12.1"

libraryDependencies += "org.scala-lang" % "scala-compiler" % "2.11.1"
