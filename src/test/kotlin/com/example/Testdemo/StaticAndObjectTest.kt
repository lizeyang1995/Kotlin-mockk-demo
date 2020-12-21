package com.example.Testdemo

import io.mockk.every
import io.mockk.mockkObject
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StaticAndObjectTest {
    @Test
    fun `test static function`() {
        val util = Util()

        mockkStatic(UtilJava::class)
        mockkStatic(UtilStaticKotlin::class)

        every { UtilJava.ok() } returns "Joe"
        every { UtilStaticKotlin.ok() } returns "Tsai"

        util.ok()

        verify { UtilJava.ok() }
        verify { UtilStaticKotlin.ok() }

        assertEquals("Joe", UtilJava.ok())
        assertEquals("Tsai", UtilStaticKotlin.ok())
    }

    @Test
    fun `test object function`() {
        val util = Util()
        mockkObject(UtilObjectKotlin)
        mockkObject(UtilObjectKotlin.Companion)

        every { UtilObjectKotlin.ok() } returns "Tsai"

        util.ok()

        assertEquals("Tsai", UtilObjectKotlin.ok())
    }
}