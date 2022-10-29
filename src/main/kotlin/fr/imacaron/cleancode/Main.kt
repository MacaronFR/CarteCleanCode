package fr.imacaron.cleancode

import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.cleanCode(){
	routing {
		get("/"){
			call.respondText { "Coucou" }
		}
	}
}