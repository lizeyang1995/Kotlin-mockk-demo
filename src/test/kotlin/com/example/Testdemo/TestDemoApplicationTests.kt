package com.example.Testdemo

import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDemoApplicationTests {

    @MockK
    lateinit var mother: Mother

    lateinit var kid: Kid

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        kid = Kid(mother)
    }

    @Test
    fun `want money`() {
        every { mother.giveMoney() } returns 30
        every { mother.inform(any()) } just Runs

        kid.wantMoney()

        assertEquals(30, kid.money)
        verify { mother.inform(any()) }

        verifyOrder {
            mother.inform(any())
            mother.giveMoney()
        }
    }

    @Test
    fun `capture the parameter`() {
        val slot = slot<Int>()
        every { mother.inform(capture(slot)) } just Runs

        kid.wantMoney()

        assertEquals(0, slot.captured)
    }

}
