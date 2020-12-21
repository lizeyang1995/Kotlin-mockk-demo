package com.example.Testdemo

import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StaticAndObjectTest {
    @Test
    fun `test ok function`() {
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
}