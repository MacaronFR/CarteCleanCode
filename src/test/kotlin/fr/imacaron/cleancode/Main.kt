package fr.imacaron.cleancode

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class MainTest{
	@Test
	fun testRoot() = testApplication {
		val response = client.get("/")
		assertEquals(HttpStatusCode.OK, response.status)
		assertEquals("Coucou", response.bodyAsText())
	}
}