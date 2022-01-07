import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    // __KOTLIN_COMPOSE_VERSION__
    kotlin("jvm") version "1.5.31"
    // __LATEST_COMPOSE_RELEASE_VERSION__
    id("org.jetbrains.compose") version ("1.0.0")
}

// use Java version that currently runs Gradle for source/target compatibility
val javaCompatibility = JavaVersion.current()
//val javaCompatibility = JavaVersion.VERSION_1_8
println(javaCompatibility)

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://maven.aliyun.com/repository/central")
    maven("https://maven.aliyun.com/repository/jcenter")
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(compose.materialIconsExtended)
    implementation("io.github.vincenzopalazzo:material-ui-swing:1.1.2")
    implementation("com.formdev:flatlaf:1.6.5")

    // if java version < 11, the sdk contain the javafx
    // https://repo1.maven.org/maven2/org/openjfx/
    if( javaCompatibility >= JavaVersion.VERSION_11 ) {
        var javafxVersion = when( javaCompatibility ) {
            JavaVersion.VERSION_11 -> "11.0.2"
            JavaVersion.VERSION_15 -> "15.0.1"
            JavaVersion.VERSION_17 -> "17.0.1"
            else -> throw RuntimeException( "JavaFX 8, 11 or 15 required (running ${javaCompatibility})" )
        }
        val osName = System.getProperty( "os.name" ).toLowerCase()
        val osArch = System.getProperty( "os.arch" ).toLowerCase()
        println(osName)
        println(osArch)
        var platform = when {
            osName.startsWith("windows") -> "win"
            osName.startsWith("mac") -> "mac"
            else -> "linux"
        }
        // M1 and Intel are distinguished only when javafxVersion is higher than 17 (Contain 17)
        if("mac" == platform && osArch == "aarch64") {
            platform = "mac-aarch64"
            javafxVersion = "17.0.1"
        }
        implementation( "org.openjfx:javafx-base:${javafxVersion}:${platform}" )
        implementation( "org.openjfx:javafx-controls:${javafxVersion}:${platform}" )
        implementation( "org.openjfx:javafx-graphics:${javafxVersion}:${platform}" )
        implementation( "org.openjfx:javafx-web:${javafxVersion}:${platform}" )
        implementation( "org.openjfx:javafx-media:${javafxVersion}:${platform}" )
        implementation( "org.openjfx:javafx-swing:${javafxVersion}:${platform}" )
    }

}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "CodeViewer-Desktop"
            packageVersion = "1.0.0"

            windows {
                menu = true
                // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                upgradeUuid = "61DAB35E-17CB-43B0-81D5-B30E1C0830FA"
            }

            macOS {
                // Use -Pcompose.desktop.mac.sign=true to sign and notarize.
                bundleID = "com.jetbrains.compose.codeviewer"
            }
        }
    }
}
