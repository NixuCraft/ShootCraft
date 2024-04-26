plugins {
    java
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))
}

repositories {
    maven("https://jitpack.io")
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.viaversion.com")

    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    // Server
    compileOnly("org.github.paperspigot:paperspigot-api:1.8.8-R0.1-SNAPSHOT")
    // iirc below include NMS
    // compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    // compileOnly("org.bukkit:bukkit:1.8.8-R0.1-SNAPSHOT")

    // Scoreboard
    val scoreboardLibraryVersion = "2.1.6"
    implementation("net.megavex:scoreboard-library-api:$scoreboardLibraryVersion")
    runtimeOnly("net.megavex:scoreboard-library-implementation:$scoreboardLibraryVersion")
    runtimeOnly("net.megavex:scoreboard-library-v1_8_R3:$scoreboardLibraryVersion")

    // Needed for both obliviate-invs & scoreboard-library
    implementation("net.kyori:adventure-platform-bukkit:4.3.2")

    // Via
    compileOnly("com.viaversion:viaversion-api:4.9.3")

    // Lombok (QOL)
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    
    // Custom libs
    val libsPath: String by project
    implementation(files("${libsPath}/NixUtils-1.0.0-all.jar"))
    implementation(files("${libsPath}/Configurator-1.1.0-all.jar"))
    implementation(files("${libsPath}/VelocityHandler-1.0.0-all.jar"))
}


tasks.build {
    dependsOn("shadowJar")
        doLast {
        val exportPath: String by project
        val buildJar = File("${projectDir}/build/libs", "${rootProject.name}-${rootProject.version}-all.jar")

        buildJar.copyTo(File(exportPath, "${rootProject.name}.jar"), true)
    }
}