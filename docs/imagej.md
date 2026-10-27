# ImageJ

ImageJ is the image processing application this course uses alongside its own Java2D code, and this document records what has been established about a stock distribution: where it is obtained, which version it carries, how it is run on a current Java, and how it is extended with compiled plugins. The findings below were measured against ImageJ 1.54g installed at `C:\bin\ImageJ` from the official Windows bundle `ij154-win-java8.zip`, on 2026-10-22; the download and version details were checked on 2026-10-27.

## Obtaining ImageJ

ImageJ 1.x lives at **<https://imagej.net/ij/>**, and its downloads are listed at **<https://imagej.net/ij/download.html>**. The wider site at `imagej.net` also covers ImageJ2 and Fiji, which are separate programs built on ImageJ 1.x rather than newer releases of it, so a search for "ImageJ" readily leads to a Fiji download instead. The pages used by this course are the ones under `/ij/`, and the archives they offer are served from `wsr.imagej.net`.

Each download is a zip archive, and installation consists of extracting it to a directory of choice; there is no installer. The archives with a bundled runtime carry their own private copy of Java, so no system Java is required and none is modified.

| Platform | Archive | Bundled Java | Size |
| --- | --- | --- | --- |
| Windows 64-bit | [`ij154-win-java8.zip`](https://wsr.imagej.net/distros/win/ij154-win-java8.zip) | Java 8 | 50,035,350 bytes |
| macOS Intel | [`ij154-osx-java8.zip`](https://wsr.imagej.net/distros/osx/ij154-osx-java8.zip) | Java 8 | 94,260,215 bytes |
| macOS Apple silicon | [`ij154-osx-arm-java13.zip`](https://wsr.imagej.net/distros/osx/ij154-osx-arm-java13.zip) | Zulu OpenJDK 13.0.6 | 60,065,930 bytes |
| Linux 64-bit | [`ij154-linux64-java8.zip`](https://wsr.imagej.net/distros/linux/ij154-linux64-java8.zip) | Java 8 | 52,605,577 bytes |
| Any, no runtime | [`ij154.zip`](https://wsr.imagej.net/distros/cross-platform/ij154.zip) | none, an installed Java is used | 6,677,151 bytes |

The sizes are the ones the server reports, and the difference between them is almost entirely the bundled runtime: the application itself, with its macros, plugins and luts, is under seven megabytes, while a bundle runs from fifty to ninety.

The platform-independent archive is the more interesting of the two for a course that already has a JDK installed, since it carries no runtime to go stale and is started in the manner described under *Running on a modern JDK* below. The Windows bundle was used here because the question being investigated was how a stock installation behaves.

## The distribution

The Windows bundle extracts to a single directory holding `ImageJ.exe`, `ij.jar`, a bundled `jre`, and the `plugins`, `macros` and `luts` directories. The bundled runtime is **Java 8** (Zulu 1.8.0_345), and `ImageJ.exe` is a launcher that starts that runtime with a fixed set of options.

## Versions and keeping current

Three version numbers are in play at once, and they differ.

| Where | Version |
| --- | --- |
| Current ImageJ 1.x release | **1.54t**, 16 May 2026 |
| The jar inside the downloadable bundles | **1.54g** |
| Latest `net.imagej:ij` on Maven Central | **1.54p**, published February 2025 |

The downloadable archives are not rebuilt for every release: both files measured above were last modified on 8 November 2023, which is why a fresh download of `ij154-win-java8.zip` installs ImageJ 1.54g rather than the current version. The version actually running is reported in the main window's status bar and by `ij.IJ.getVersion()`.

ImageJ updates itself rather than being reinstalled. **Help > Update ImageJ...** fetches the list of offered versions from `wsr.imagej.net/jars/list.txt`, downloads the chosen `ij.jar`, and replaces the installed one, the new version taking effect at the next start. The list is short and deliberately so, holding one entry per recent line: at the time of checking, v1.54t, v1.53v, v1.52v, v1.51w, v1.50i and v1.49v. The same command offers the daily build, and because it both upgrades and downgrades it is also the way back when a new release misbehaves.

The project compiles against `net.imagej:ij` declared in `pom.xml`, and the artifact on Maven Central lags the release: 1.54p, published in February 2025, against 1.54t upstream. The gap has caused no difficulty for these examples, but code compiled against one version and run inside another is the ordinary situation here rather than an unusual one, and a plugin relying on an API added between the two would compile and then fail at run time.

## Running on a modern JDK

**Pointing `ImageJ.exe` at a newer runtime does not work**, although it appears that it should. An `ImageJ.cfg` naming a Java 21 executable is honored, and the launcher then adds its own options, among them `-Xincgc`, which was removed after Java 8. The result is `Unrecognized option: -Xincgc` followed by `Could not create the Java Virtual Machine`, and no window. The string `Xincgc` is present in the launcher binary itself, which is dated June 2015, so the option is compiled in rather than configured and no edit to `ImageJ.cfg` can remove it.

The obstacle is therefore the launcher and not the application. ImageJ 1.x runs on current Java, and the application class can be started directly:

```
javaw.exe -Xmx2048m -Dplugins.dir=C:\bin\ImageJ\. -cp C:\bin\ImageJ\ij.jar ij.ImageJ
```

The working directory is the installation directory, so that the macros and luts directories resolve. The `plugins.dir` property is passed explicitly because the launcher normally supplies it; without it the plugins directory is sought relative to the working directory, which is correct only when the command is issued from the installation.

A script holding that command is installed beside the launcher as `ImageJ-jdk21.bat`, and starting it gives ImageJ 1.54g on Java 21.0.10, reported in the status bar, with the same 474 commands and 72 macros the Java 8 launcher produces. A plugin jar compiled for Java 8, such as the one described below, is read by the newer runtime without difficulty: both course entries register under Java 21 exactly as they do under Java 8, since a Java runtime reads class files older than itself.

What is lost by this route is the launcher's own services, which are modest: the single-instance handoff, by which a second start passes its arguments to the instance already running, and the memory setting stored in `ImageJ.cfg`, which the script sets directly instead. What is gained is a current runtime, Java 8 having been out of public support for some years.

## Installing compiled plugins

Compiled plugins can be installed into a stock ImageJ distribution, and the two examples in `org.au.csc360.ij` have been installed into one and run from it. Three conditions govern whether a plugin appears and whether it loads, and each of them fails quietly, which is what makes them worth recording.

### Condition 1: the jar file name must contain an underscore

A plugin jar placed in `plugins/` or `plugins/jars/` is scanned only if its file name contains an underscore. The requirement is the same one that governs loose class files, and it is applied to the archive rather than to its contents.

This was established by installing one jar under two names and reading back `ij.Menus.getCommands()`. As `csc360-plugins.jar` the command table held 512 entries and neither example appeared; renamed to `csc360_plugins.jar`, with no other change, the table held 514 and both appeared. Nothing was logged in either case, so the only symptom of the wrong name is a menu that does not change. The bundled `auto_threshold.jar` follows the same rule, while `BeanShell.jar`, which carries no underscore, is a library rather than a plugin provider.

### Condition 2: a `plugins.config` entry names the menu position and the class

A file named `plugins.config` at the root of the jar maps menu entries to classes, one per line, as `Menu>Submenu, "Label", fully.qualified.ClassName`. Lines beginning with `#` are comments. The configuration used for these examples is:

```
Plugins>CSC360, "Ramp Image (PlugIn)", org.au.csc360.ij.Ramp_Image_PlugIn
Plugins>CSC360, "Luminance Grayscale (PlugInFilter)", org.au.csc360.ij.Luminance_Grayscale_PlugInFilter
```

Because the configuration names the class in full, a plugin delivered this way may live in any package, which is what allows these examples to keep the project's `org.au.csc360.ij` package rather than being flattened into the default package that loose class files in `plugins/` require. A submenu named in the first field is created where it does not already exist, so `Plugins>CSC360` gathers the course's plugins under one heading.

### Condition 3: the class files must be readable by the runtime that ImageJ is using

This is the condition most likely to be met by accident and the one whose failure is least informative. The project compiles at `maven.compiler.release` 17, which produces class file version 61, and the runtime bundled with ImageJ reads version 52 at most. A plugin jar built from the project's ordinary output is scanned, appears in the menu, and then fails when the entry is selected:

```
java.lang.UnsupportedClassVersionError: org/au/csc360/ij/Ramp_Image_PlugIn has been compiled by
a more recent version of the Java Runtime (class file version 61.0), this version of the Java
Runtime only recognizes class file versions up to 52.0
```

**Compiling the plugin sources for Java 8** produces class file version 52, which the bundled runtime loads: the two examples, compiled with `--release 8`, registered both menu entries and ran under Java 1.8.0_345 on the unmodified distribution. This is the approach the project takes, and it is why `Luminance_Grayscale_PlugInFilter` calls `Paths.get` where the rest of the project calls `Path.of`, the latter having arrived in Java 11. Plugin sources are therefore held to Java 8 even though the project as a whole is not.

That restriction is a deployment choice rather than an absolute. A plugin compiled for Java 8 loads on both runtimes, while one compiled for 17 loads only on the newer, so the lower target buys compatibility with the stock launcher and nothing else. Where the application is always started as *Running on a modern JDK* above describes, plugin sources may use the same language level as the rest of the project.

### Building and installing the jar

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
