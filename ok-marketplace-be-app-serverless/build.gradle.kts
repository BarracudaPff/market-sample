import com.github.gradle.node.npm.task.NpxTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("com.github.node-gradle.node") version "3.0.1"
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(":ok-marketplace-be-common"))
    implementation(project(":ok-marketplace-be-transport-openapi"))
    implementation(project(":ok-marketplace-be-transport-mapping-openapi"))
    implementation(project(":ok-marketplace-be-stubs"))

    implementation("com.amazonaws:aws-lambda-java-core:1.2.1")
    implementation("com.amazonaws:aws-lambda-java-log4j:1.0.1")
    implementation("com.amazonaws:aws-lambda-java-events:3.9.0")

    implementation("com.fasterxml.jackson.core:jackson-core:2.12.4")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.4")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.12.4")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.4")
}

node {
    download.set(false)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

val buildZip by tasks.creating(Zip::class) {
    archiveBaseName.set("functions")
    from(tasks.named("compileKotlin"))
    from(tasks.named("processResources"))
    into("lib") {
        from(configurations.runtimeClasspath)
    }
}

val build by tasks.getting {
    dependsOn(buildZip)
}

val serverlessDeploy by tasks.creating(NpxTask::class) {
    dependsOn(build, "npmInstall")

    command.set("serverless")
    args.set(listOf("deploy"))
    environment.put("ARTIFACT", buildZip.archiveFile.map { it.asFile.absolutePath })
}