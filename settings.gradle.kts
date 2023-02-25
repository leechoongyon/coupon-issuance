rootProject.name = "coupon-issuance-toy-project"

pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings
    val asciidoctorConvertVersion: String by settings
    val kotlinterVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.kapt" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.jpa" -> useVersion(kotlinVersion)
                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
                "org.asciidoctor.jvm.convert" -> useVersion(asciidoctorConvertVersion)
                "org.jmailen.kotlinter" -> useVersion(kotlinterVersion)
            }
        }
    }
}

include("coupon-issuance-request")
include("coupon-issuance-request:core")
findProject(":coupon-issuance-request:core")?.name = "core"
include("coupon-issuance-request:core:core-api")
findProject(":coupon-issuance-request:core:core-api")?.name = "core-api"
include("coupon-issuance-request:storage")
findProject(":coupon-issuance-request:storage")?.name = "storage"
include("coupon-issuance-request:clients")
findProject(":coupon-issuance-request:clients")?.name = "clients"
include("coupon-issuance")
include("coupon-issuance:core")
findProject(":coupon-issuance:core")?.name = "core"
include("coupon-issuance:core:core-api")
findProject(":coupon-issuance:core:core-api")?.name = "core-api"
include("coupon-issuance:storage")
findProject(":coupon-issuance:storage")?.name = "storage"
include("coupon-issuance:clients")
findProject(":coupon-issuance:clients")?.name = "clients"
include("coupon-issuance:clients")
findProject(":coupon-issuance:clients")?.name = "clients"
include("coupon-issuance:storage:db-core")
findProject(":coupon-issuance:storage:db-core")?.name = "db-core"
include("coupon-issuance-request:storage:db-core")
findProject(":coupon-issuance-request:storage:db-core")?.name = "db-core"
include("coupon-issuance-request:tests")
findProject(":coupon-issuance-request:tests")?.name = "tests"
include("coupon-issuance:tests")
findProject(":coupon-issuance:tests")?.name = "tests"
include("coupon-issuance-request:tests:api-docs")
findProject(":coupon-issuance-request:tests:api-docs")?.name = "api-docs"
