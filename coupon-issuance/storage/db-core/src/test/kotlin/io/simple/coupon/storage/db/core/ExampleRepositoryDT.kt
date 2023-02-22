package io.simple.coupon.storage.db.core

import io.simple.coupon.storage.db.CoreDbContextTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ExampleRepositoryDT(
    val exampleRepository: ExampleRepository
) : CoreDbContextTest() {
    @Test
    fun testShouldBeSavedAndFound() {
        var saved = exampleRepository.save(ExampleEntity("SIMPLE"))
        Assertions.assertEquals("SIMPLE", saved.exampleColumn)
        assertThat(saved.exampleColumn).isEqualTo("SIMPLE")

        val found = exampleRepository.findById(saved.id!!).get()
        assertThat(found.exampleColumn).isEqualTo("SIMPLE")
    }
}
