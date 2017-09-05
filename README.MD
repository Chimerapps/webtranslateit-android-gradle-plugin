# webtranslateit android gradle plugin

A simple plugin which downloads translations from webtranslateit and saves the files to the project.

Usage:
```
webtranslateit {
    apiKey 'read-only-key'
    fileName 'strings.xml'       //Default
    sourceRoot 'src/main/res'    //Default

    configuration("test") {
        apiKey 'read-only-key2'     //Uses the default fileName and sourceRoot. Not inherited
    }

    configuration("release") {
        apiKey 'read-only-key3'     //Uses the default fileName and sourceRoot. Not inherited
    }
}
```

You can then call the tasks from the command line:
```
./gradlew updateTranslations            //Runs all translations tasks
./gradlew updateDefaultTranslations     //Runs the translation task defined by the outer scope
./gradlew updateTestTranslations        //Runs the translation task defined by the 'test' configuration
./gradlew updateReleaseTranslations     //Runs the translation task defined by the 'release' configuration
```

**NOTE:** If the api key is missing from the outer scope, the 'default' configuration is not included as task 