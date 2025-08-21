# Role Model Preview Plugin for IntelliJ IDEA

This plugin provides a preview for role model microservice descriptions defined in YAML files.

## Features

- Preview role models with their associated permissions
- Resolves role references from the role dictionary
- Displays platform codes and role codes in the header
- Shows all permissions associated with each role (including those from permission groups)

## Installation

1. Build the plugin using Gradle:
   ```
   ./gradlew buildPlugin
   ```

2. In IntelliJ IDEA, go to `Settings` > `Plugins` > `Install Plugin from Disk`
3. Select the generated ZIP file from `build/distributions/`

## Usage

1. Create a role model YAML file named `role_model.yaml`
2. Create a role dictionary YAML file named `role_dictionary.yaml` in the parent directory
3. Open the role model file in IntelliJ IDEA
4. The preview will automatically appear as a tab next to the editor

## Role Model Structure

The plugin expects role models in the following YAML structure:

```yaml
permissions:
  - code: GetConsGroup
    name: Получение консолидированной группы

  - code: SearchConsGroup
    name: Поиск консолидированных групп по параметрам

permission-groups:
  - code: clientsRead
    name: Права на чтение в clients
    permission-refs:
      - GetConsGroup

roles:
  - code: technical_role_alpha
    permission-group-refs:
      - clientsRead
    permission-refs:
      - SearchConsGroup
```

## Role Dictionary Structure

The role dictionary should be in the parent directory and have the following structure:

```yaml
roles:
  - code: technical_role_alpha
    name: Технический пользователь
    sudirRoles:
      - platformCode: PPRB
        roleCode: pprbmer4gen_technical_role
      - platformCode: EFS
        roleCode: efs_technical_role_alpha
    description:
      - выполнение фоновых процессов
```

## Preview Format

The preview will display:

1. Header with platform code and role code from the role dictionary
2. List of all permissions associated with each role (including those from permission groups)

## Building from Source

To build the plugin from source:

1. Ensure you have Java 17 and Gradle installed
2. Clone this repository
3. Run `./gradlew buildPlugin` in the project directory
4. The plugin ZIP will be generated in `build/distributions/`