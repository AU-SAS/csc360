package jfx;

import javafx.application.Application;

import java.util.Arrays;

/**
 * Entry point for the JavaFX demos. Run this, not the demo classes directly.
 *
 * <p>Running a demo class directly with the JavaFX jars on the classpath fails with:
 *
 * <pre>Error: JavaFX runtime components are missing, and are required to run this application</pre>
 *
 * <p>That message is misleading -- the jars are present and on the classpath. The check lives in
 * {@code sun.launcher.LauncherHelper} and fires when <em>the main class named on the command line</em>
 * is a subclass of {@link Application} while the {@code javafx.graphics} module is not in the module
 * graph. Since JDK 9, JavaFX is a set of modules; putting its jars on {@code -classpath} loads the
 * classes but never builds the module graph, so the check trips. IntelliJ builds a {@code -classpath}
 * line from the Maven dependencies, which is why "Run" on a demo class hits this and
 * {@code mvn javafx:run} does not -- the plugin passes {@code --module-path} and {@code --add-modules}.
 *
 * <p>The launcher sidesteps it: this class is not an {@code Application} subclass, so the check never
 * applies, and JavaFX then initialises perfectly well from the classpath. No VM options, no per-machine
 * run-configuration setup.
 *
 * <p>Pass a demo's simple class name to pick one; with no argument it runs the hello-world demo.
 *
 * <pre>
 *   java -cp ... jfx.Launcher
 *   java -cp ... jfx.Launcher Step_2_JavaFX_Controls
 * </pre>
 */
public class Launcher {

    private static final String DEFAULT_DEMO = "Step_0_JavaFX_Hello_World";

    public static void main(String[] args) throws ClassNotFoundException {
        String demo = args.length > 0 ? args[0] : DEFAULT_DEMO;
        String[] rest = args.length > 0 ? Arrays.copyOfRange(args, 1, args.length) : args;

        Class<? extends Application> app =
                Class.forName(Launcher.class.getPackageName() + "." + demo).asSubclass(Application.class);

        Application.launch(app, rest);
    }
}
