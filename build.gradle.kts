@file:Suppress("UnstableApiUsage")

plugins {
    id("java")
    id("fabric-loom") version "1.5-SNAPSHOT"
    id("io.spring.javaformat") version "0.0.41"
}

version = project.property("mod_version")!!
group = project.property("mod_group")!!

java {
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17

    toolchain.languageVersion = JavaLanguageVersion.of(17)
}

tasks.withType<JavaCompile>().configureEach {
    options.release = 17
}

tasks.withType<Jar> {
    from("LICENSE")

    archiveBaseName.set(project.property("mod_id")!! as String)
}

loom {
    accessWidenerPath = file("src/main/resources/dumbpotions.accesswidener")
}

repositories {
    maven {
        url = uri("https://cursemaven.com")
    }

    maven {
        url = uri("https://maven.parchmentmc.org")
    }

    maven {
        url = uri("https://maven.shedaniel.me")
    }

    maven {
        url = uri("https://jitpack.io")
    }

    maven {
        url = uri("https://maven.draylar.dev/releases")
    }

    maven {
        url = uri("https://masa.dy.fi/maven")
    }

    maven {
        url = uri("https://server.bbkr.space/artifactory/libs-release")
    }

    maven {
        url = uri("https://maven.wispforest.io")
    }
}

dependencies {
    minecraft("com.mojang:minecraft:${project.property("minecraft_version")}")

    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-1.20.1:2023.09.03@zip")
    })

    modImplementation("net.fabricmc:fabric-loader:${project.property("loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${project.property("fabric_api_version")}")
    modImplementation("io.wispforest:owo-lib:0.11.2+1.20")

    modApi("curse.maven:identity-391390:4687497")
    modApi("carpet:fabric-carpet:1.20-1.4.112+v230608")

    modCompileOnly("com.github.Virtuoel:Pehkui:3.8.0")
    modRuntimeOnly("com.github.Virtuoel:KanosConfig:0.4.1")
    modRuntimeOnly("curse.maven:pehkui-319596:5208259")
    modRuntimeOnly("dev.draylar.omega-config:omega-config-base:1.4.0+1.20.1")
    modRuntimeOnly("dev.architectury:architectury-fabric:9.2.14")
}

tasks.withType<ProcessResources> {
    inputs.property("version", project.version)
    inputs.property("mod_id", project.property("mod_id"))
    inputs.property("mod_name", project.property("mod_name"))

    filesMatching("fabric.mod.json") {
        expand(mapOf(
            "version" to project.version,
            "mod_id" to project.property("mod_id"),
            "mod_name" to project.property("mod_name"),
        ))
    }
}
