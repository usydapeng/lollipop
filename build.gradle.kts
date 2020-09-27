//import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
//import org.gradle.api.tasks.testing.logging.TestLogEvent.*
//import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
//import com.google.protobuf.gradle.*
//
//plugins {
//  kotlin("jvm") version "1.4.10"
//  kotlin("kapt") version "1.4.10"
//  application
//  id("com.github.johnrengelman.shadow") version "5.2.0"
//  id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
//  id("io.ebean") version "12.3.6"
//  id("com.google.protobuf") version "0.8.12"
//}
//
//group = "org.zunpeng.vertx"
//version = "0.0.1"
//
//repositories {
//  mavenCentral()
//  jcenter()
//}
//
//val kotlinVersion = "1.4.10"
//val vertxVersion = "4.0.0.Beta3"
//val junitJupiterVersion = "5.6.0"
//val ebeanVersion = "12.3.6"
//val kotlinLoggingVersion = "1.7.8"
//val logbackVersion = "1.2.3"
//val micrometerVersion = "1.5.3"
//val grpcKotlinVersion = "0.2.0"
//val grpcVersion = "1.32.1"
//val protobufVersion = "3.13.0"
//
//val mainVerticleName = "org.zunpeng.MainVerticle"
//val watchForChange = "src/**/*"
//val doOnChange = "./gradlew classes"
//val launcherClassName = "io.vertx.core.Launcher"
//
//application {
//  mainClassName = launcherClassName
//}
//
//dependencies {
//  implementation("io.vertx:vertx-web-client:$vertxVersion")
//  implementation("io.vertx:vertx-web-validation:$vertxVersion")
//  implementation("io.vertx:vertx-service-proxy:$vertxVersion")
//  implementation("io.vertx:vertx-health-check:$vertxVersion")
//  implementation("io.vertx:vertx-web:$vertxVersion")
//  implementation("io.vertx:vertx-web-openapi:$vertxVersion")
//  implementation("io.vertx:vertx-service-discovery:$vertxVersion")
//  implementation("io.vertx:vertx-micrometer-metrics:$vertxVersion")
//  implementation("io.vertx:vertx-json-schema:$vertxVersion")
//  implementation("io.vertx:vertx-shell:$vertxVersion")
//  implementation("io.vertx:vertx-web-api-contract:$vertxVersion")
//  implementation("io.vertx:vertx-jdbc-client:$vertxVersion")
//  implementation("io.vertx:vertx-config:$vertxVersion")
//  implementation("io.vertx:vertx-config-hocon:$vertxVersion")
//  implementation("io.vertx:vertx-web-graphql:$vertxVersion")
//  implementation("io.vertx:vertx-web-templ-pebble:$vertxVersion")
//  implementation("io.vertx:vertx-sql-client-templates:$vertxVersion")
//  implementation("io.vertx:vertx-web-sstore-cookie:$vertxVersion")
//  implementation("io.vertx:vertx-circuit-breaker:$vertxVersion")
//  implementation("io.vertx:vertx-lang-kotlin-coroutines:$vertxVersion")
//  implementation("io.vertx:vertx-mail-client:$vertxVersion")
//  implementation("io.vertx:vertx-auth-sql-client:$vertxVersion")
//  implementation("io.vertx:vertx-web-sstore-redis:$vertxVersion")
//  implementation("io.vertx:vertx-rabbitmq-client:$vertxVersion")
//  implementation("io.vertx:vertx-lang-kotlin:$vertxVersion")
//  implementation("io.vertx:vertx-grpc:$vertxVersion")
//  implementation(kotlin("stdlib-jdk8"))
//
//  // service proxy
//  // generate [service proxy]
//  kapt("io.vertx:vertx-codegen:$vertxVersion:processor")
//  // kotlin coroutine await [service proxy]
//  kapt("io.vertx:vertx-lang-kotlin-gen:$vertxVersion")
//  compileOnly("io.vertx:vertx-codegen:$vertxVersion")
//
//  // ebean.io
//  implementation("io.ebean:ebean:$ebeanVersion")
//  implementation("io.ebean:ebean-querybean:$ebeanVersion")
//  kapt("io.ebean:kotlin-querybean-generator:$ebeanVersion")
//
//  // log
//  implementation("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")
//  implementation("ch.qos.logback:logback-classic:$logbackVersion")
//
//  // db
//  implementation("mysql:mysql-connector-java:6.0.6")
//  implementation("com.zaxxer:HikariCP:3.4.5")
//  implementation("org.litote.kmongo:kmongo-async:4.1.2")
//
//  // grpc
//  implementation("com.google.protobuf:protobuf-java-util:$protobufVersion")
//  implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
//  implementation("io.grpc:grpc-core:$grpcVersion")
//  implementation("io.grpc:grpc-protobuf:$grpcVersion")
//  implementation("io.grpc:grpc-stub:$grpcVersion")
//
//  // other
//  implementation("com.alibaba:easyexcel:2.2.4")
//  implementation("com.github.mjeanroy:exiftool-lib:2.1.0")
//  implementation("io.micrometer:micrometer-registry-influx:$micrometerVersion")
//  implementation("com.google.inject:guice:4.2.3")
//
//  testImplementation("io.vertx:vertx-junit5:$vertxVersion")
//  testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
//  testImplementation("io.ebean:ebean-test:$ebeanVersion")
//}
//
//val compileKotlin: KotlinCompile by tasks
//compileKotlin.kotlinOptions.jvmTarget = "11"
//
//val compileTestKotlin: KotlinCompile by tasks
//compileTestKotlin.kotlinOptions.jvmTarget = "11"
//
//tasks.withType<ShadowJar> {
//  archiveClassifier.set("fat")
//  manifest {
//    attributes(mapOf("Main-Verticle" to mainVerticleName))
//  }
//  mergeServiceFiles {
//    include("META-INF/services/io.vertx.core.spi.VerticleFactory")
//  }
//}
//
//tasks.withType<Test> {
//  useJUnitPlatform()
//  testLogging {
//    events = setOf(PASSED, SKIPPED, FAILED)
//  }
//}
//
//tasks.withType<JavaExec> {
//  args = listOf(
//    "run",
//    mainVerticleName,
//    "--redeploy=$watchForChange",
//    "--launcher-class=$launcherClassName",
//    "--on-redeploy=$doOnChange"
//  )
//}
//
//kapt {
//  javacOptions {
//    option("-proc:only")
//    option("-processor", "io.vertx.codegen.CodeGenProcessor")
//    option("-Acodegen.output", "${project.projectDir}/src/main")
//    option("-AoutputDirectory", "$projectDir/src/main")
//  }
//  arguments {
//    arg("destinationDir", "src/main/generated")
//  }
//}
//
//ebean {
//  debugLevel = 1
//}
//
//protobuf {
//  protoc {
//    artifact = "com.google.protobuf:protoc:$protobufVersion"
//  }
//  plugins {
//    id("grpc") {
//      artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"
//    }
//    id("grpckt") {
//      artifact = "io.grpc:protoc-gen-grpc-kotlin:$grpcKotlinVersion"
//    }
//  }
//  generateProtoTasks {
//    ofSourceSet("main").forEach {
//      it.plugins {
//        id("grpc")
//        id("grpckt")
//      }
//    }
//  }
//}
//
//tasks.register("printVersion") {
//  doLast {
//    println(version)
//  }
//}
