**The information on this page is outdated; the Symja project has moved to [BitBucket.org](https://bitbucket.org/axelclk/symja_android_library).**



# The console application #
In the folder where you've unzipped the downloaded [ZIP file](http://symja.googlecode.com/files/meconsole-0.0.9.zip), run a command similar to this:
```
C:\Java\jdk1.6.0\jre\bin\java -classpath meconsole010.jar;jsr305.jar;google-collect-1.0-rc3.jar;commons-logging-1.1.1.jar;log4j-1.2.11.jar org.matheclipse.core.eval.Console
```

The following console session appears:
```
org.matheclipse.core.eval.Console [options]

Program arguments: 
  -h or -help                  print this message
  -f or -file <filename>       use given file as input script
  -d or -default <filename>    use given textfile for system rules
To stop the program type: 
exit<RETURN>
To continue an input line type '\' at the end of the line.
****+****+****+****+****+****+****+****+****+****+****+****+
>>> 1+1
In [1]: 1+1
Out[1]: 2
>>> D[Sin[x],x]
In [2]: D[Sin[x],x]
Out[2]: Cos[x]
>>> 
```

## Program options ##
With the `-f` or `-file` program argument, you can run a default script on startup of the console or Symja application:
```
 -f C:\temp\mymath.script
```

With the `-d` or `-default` program argument, the default pattern-matching math rules which are implemented in the [System.mep](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/System.mep) file are replaced by your own rules:
```
 -default C:\temp\mysystem.mep
```

# The jrunscript console application (JSR 223 scripting engine) #
In the folder where you've unzipped the downloaded [ZIP file](http://symja.googlecode.com/files/meconsole-0.0.9.zip), run a command similar to this:
```
C:\Java\jdk1.6.0\bin\jrunscript -classpath meconsole010.jar;jsr305.jar;google-collect-1.0-rc3.jar;commons-logging-1.1.1.jar;log4j-1.2.11.jar -l "matheclipse script"
```

The following console session appears:
```
matheclipse script> D[Sin[x],x]
Cos[x]
matheclipse script>
```

For using the scripting engine from Java see the document about [JSR223 Support](JSR223Support.md)

# The Symja Swing GUI application #
In the folder where you installed the **symja** JAR, run a command similar to this:
```
C:\Java\jdk1.6.0\jre\bin\java -classpath symja-0.0.7a.jar org.matheclipse.symja.Main
```