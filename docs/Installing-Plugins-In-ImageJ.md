# Installing compiled plugins into an ImageJ distribution

Compiled plugins can be installed into a stock ImageJ distribution, and the two examples in `org.au.csc360.ij` have been installed into one and run from it. Three conditions govern whether a plugin appears and whether it loads, and each of them fails quietly, which is what makes them worth recording. The findings below were measured against ImageJ 1.54g installed at `C:\bin\ImageJ` from the official Windows bundle `ij154-win-java8.zip`, on 2026-10-22.

## The distribution

The Windows bundle extracts to a single directory holding `ImageJ.exe`, `ij.jar`, a bundled `jre`, and the `plugins`, `macros` and `luts` directories. The bundled runtime is **Java 8** (Zulu 1.8.0_345), and `ImageJ.exe` is a launcher that starts that runtime with a fixed set of options.

The jar shipped in the bundle is ImageJ **1.54g**, while the Maven dependency this project compiles against is `net.imagej:ij:1.54p`. The difference has not caused a problem for these examples, but the two are not the same build, and a plugin that relies on an API added between the two would compile and then fail at run time.

## Condition 1: the jar file name must contain an underscore

A plugin jar placed in `plugins/` or `plugins/jars/` is scanned only if its file name contains an underscore. The requirement is the same one that governs loose class files, and it is applied to the archive rather than to its contents.

This was established by installing one jar under two names and reading back `ij.Menus.getCommands()`. As `csc360-plugins.jar` the command table held 512 entries and neither example appeared; renamed to `csc360_plugins.jar`, with no other change, the table held 514 and both appeared. Nothing was logged in either case, so the only symptom of the wrong name is a menu that does not change. The bundled `auto_threshold.jar` follows the same rule, while `BeanShell.jar`, which carries no underscore, is a library rather than a plugin provider.

## Condition 2: a `plugins.config` entry names the menu position and the class

A file named `plugins.config` at the root of the jar maps menu entries to classes, one per line, as `Menu>Submenu, "Label", fully.qualified.ClassName`. Lines beginning with `#` are comments. The configuration used for these examples is:

```
Plugins>CSC360, "Ramp Image (PlugIn)", org.au.csc360.ij.Ramp_Image_PlugIn
Plugins>CSC360, "Luminance Grayscale (PlugInFilter)", org.au.csc360.ij.Luminance_Grayscale_PlugInFilter
```

Because the configuration names the class in full, a plugin delivered this way may live in any package, which is what allows these examples to keep the project's `org.au.csc360.ij` package rather than being flattened into the default package that loose class files in `plugins/` require. A submenu named in the first field is created where it does not already exist, so `Plugins>CSC360` gathers the course's plugins under one heading.

## Condition 3: the class files must be readable by the runtime that ImageJ is using

This is the condition most likely to be met by accident and the one whose failure is least informative. The project compiles at `maven.compiler.release` 17, which produces class file version 61, and the runtime bundled with ImageJ reads version 52 at most. A plugin jar built from the project's ordinary output is scanned, appears in the menu, and then fails when the entry is selected:

```
java.lang.UnsupportedClassVersionError: org/au/csc360/ij/Ramp_Image_PlugIn has been compiled by
a more recent version of the Java Runtime (class file version 61.0), this version of the Java
Runtime only recognizes class file versions up to 52.0
```

Two resolutions exist and only one of them holds. **Compiling the plugin sources for Java 8** produces class file version 52, which the bundled runtime loads: the two examples, compiled with `--release 8`, registered both menu entries and ran under Java 1.8.0_345 on the unmodified distribution. This is the approach the project takes, and it is why `Luminance_Grayscale_PlugInFilter` calls `Paths.get` where the rest of the project calls `Path.of`, the latter having arrived in Java 11. Plugin sources are therefore held to Java 8 even though the project as a whole is not.

**Pointing ImageJ at a newer runtime does not work**, although it appears to. An `ImageJ.cfg` naming a Java 21 executable is honored by the launcher, and the launcher then adds its own options, among them `-Xincgc`, which was removed after Java 8. The result is `Unrecognized option: -Xincgc` followed by `Could not create the Java Virtual Machine`, and no window. ImageJ itself runs perfectly well on Java 21 when started directly as `java -cp ij.jar ij.ImageJ`, so the obstacle is the launcher rather than the application, but the bundled `ImageJ.exe` cannot be made to use a modern runtime by configuration alone.

## Building and installing the jar

The two plugin classes are compiled for Java 8 against the installed `ij.jar`, and packaged with the configuration file at the root of the archive:

```
javac --release 8 -d classes -cp C:\bin\ImageJ\ij.jar \
    src/main/java/org/au/csc360/ij/Ramp_Image_PlugIn.java \
    src/main/java/org/au/csc360/ij/Luminance_Grayscale_PlugInFilter.java
jar cf csc360_plugins.jar plugins.config -C classes org
```

The resulting `csc360_plugins.jar` is copied into `C:\bin\ImageJ\plugins\`, and the entries appear under Plugins > CSC360 when ImageJ next starts. The project's own `mvn package` does not currently produce this jar; it is built separately, because the release level and the file name both differ from the project's main artifact.

## A note on the firewall prompt

ImageJ opens a local listening socket on startup so that a second launch can hand its arguments to the instance already running. On Windows this raises a Defender Firewall prompt the first time `ImageJ.exe` starts. Declining it leaves inbound Block rules in place and has no effect on anything described here, since none of it depends on that socket; the only consequence is that each launch starts an independent instance.
