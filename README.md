# HyperLethalAuto32K
This is a useless auto 32k mod beacuse you cant use 32ks in 2b2t,just for fun.

## Building
We recommend using [IntelliJ](https://www.jetbrains.com/idea/download/). Eclipse will require additional steps to set up, which we won't cover here.

- Import build.gradle
- Run `gradlew setupDecompWorkspace`
- Run `gradlew genIntelliJRuns`
- **If** configurations are broken, change the classpath of module to "main"
- Run the HyperLethalAuto32K

If it fails at any point, try giving gradle more RAM via `-Xmx3G`

## Contributing
This project is not meant to be contributed upon. It's meant to act as a base for anyone who'd like to make their own client. The only contributions that will be accepted are bug fixes or updates for different minecraft versions.
