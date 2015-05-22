**The information on this page is outdated; the Symja project has moved to [BitBucket.org](https://bitbucket.org/axelclk/symja_android_library).**

# build\_mini.xml ANT script #
To reduce the size of the `org.matheclipse.core.eval.Console` application we can use the [ProGuard](http://proguard.sourceforge.net/) tool.

The `obfuscate` task in the [build\_mini.xml](http://code.google.com/p/symja/source/browse/trunk/build_mini.xml) ANT script creates a `build/jar/Symja.jar` and shrinked jar `build/jar/Symja_mini.jar`.

To run the shrinked Console application you can use a command similar to the following:

`C:\Java\jdk1.6.0\bin>java -jar C:\eclipse\workspace\symja\build\jar\Symja_mini.jar`

At the moment the ANT script has the following drawbacks:
  * unfortunately I had to set the `-dontwarn` [ProGuard](http://proguard.sourceforge.net/) option to get it to run