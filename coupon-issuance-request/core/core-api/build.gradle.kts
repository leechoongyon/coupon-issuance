tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":coupon-issuance-request:storage:db-core"))
    implementation(project(":coupon-issuance-request:storage:redis-core"))
    testImplementation(project(":coupon-issuance-request:tests:api-docs"))

    implementation("org.springframework.boot:spring-boot-starter-web")
}
