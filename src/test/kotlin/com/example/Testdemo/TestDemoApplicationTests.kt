package com.example.Testdemo

import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDemoApplicationTests {

	@Test
	fun `want money`() {
		val mother = mockk<Mother>()
		val kid = Kid(mother)
		every {mother.giveMoney()} returns 30
		every {mother.inform(any())} just Runs

		kid.wantMoney()

		assertEquals(30, kid.money)
		verify {mother.inform(any())}
	}

}
