package fr.imacaron.cleancode

import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.apache.commons.dbcp2.BasicDataSource
import org.ktorm.database.Database
import org.ktorm.database.SqlDialect
import org.ktorm.support.mysql.MySqlDialect

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module(){
	attributes.put(databaseAttributeKey,createDBConnection())
	cleanCode()
	install(Resources)
}

fun Application.cleanCode(){
	println(attributes[databaseAttributeKey].dialect)
	routing {
		get("/"){
			call.respondText { "Coucou" }
		}
	}
}

fun Application.createDBConnection(dialect: SqlDialect = MySqlDialect()): Database{
	val ds = BasicDataSource()
	ds.url = environment.config.property("database.url").getString()
	ds.username = environment.config.property("database.user").getString()
	ds.password = environment.config.property("database.password").getString()
	ds.minIdle = 5
	ds.maxIdle = 10
	ds.maxOpenPreparedStatements = 100
	return Database.connect(ds, dialect)
}