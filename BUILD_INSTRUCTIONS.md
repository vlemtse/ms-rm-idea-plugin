# How to Build and Run the Plugin

## Prerequisites

1. Java 17 JDK installed
2. Gradle installed (or use the Gradle wrapper)

## Building the Plugin

### Option 1: Using Gradle Wrapper (Recommended)

1. Open a terminal/command prompt in the project directory
2. On Windows, run:
   ```
   gradlew.bat buildPlugin
   ```
   
   On macOS/Linux, run:
   ```
   ./gradlew buildPlugin
   ```

### Option 2: Using Installed Gradle

1. Open a terminal/command prompt in the project directory
2. Run:
   ```
   gradle buildPlugin
   ```

## Output

After a successful build, the plugin ZIP file will be located at:
```
build/distributions/ms-rm-idea-plugin-1.0-SNAPSHOT.zip
```

## Installing the Plugin in IntelliJ IDEA

1. Open IntelliJ IDEA
2. Go to `File` > `Settings` (or `IntelliJ IDEA` > `Preferences` on macOS)
3. Navigate to `Plugins`
4. Click the gear icon and select `Install Plugin from Disk...`
5. Select the ZIP file generated in the build step
6. Restart IntelliJ IDEA

## Using the Plugin

1. Create a role model YAML file named `role_model.yaml`
2. Create a role dictionary YAML file named `role_dictionary.yaml` in the parent directory
3. Open the role model file in IntelliJ IDEA
4. The preview will automatically appear as a tab next to the editor

## Development

To make changes to the plugin:

1. Import the project into IntelliJ IDEA as a Gradle project
2. Make your changes to the Java files
3. Rebuild the plugin using the steps above
4. Reinstall the plugin in IntelliJ IDEA