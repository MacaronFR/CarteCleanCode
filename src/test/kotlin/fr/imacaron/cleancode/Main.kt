package fr.imacaron.cleancode

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.testing.*
import org.ktorm.database.Database
import org.ktorm.support.sqlite.SQLiteDialect
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MainTest{
	@BeforeTest
	fun init(){
		testApplication {
			application {
				attributes.put(databaseAttributeKey, createDBConnection(SQLiteDialect()))
			}
		}
	}

	@Test
	fun testRoot() = testApplication {
		application {
			attributes.put(databaseAttributeKey, createDBConnection(SQLiteDialect()))
		}
		val response = client.get("/")
		assertEquals(HttpStatusCode.OK, response.status)
		assertEquals("Coucou", response.bodyAsText())
	}

	fun test() {
		testApplication {

		}
	}
}