package fr.imacaron.cleancode

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.util.*
import kotlinx.serialization.SerializationException
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import org.ktorm.database.Database

val databaseAttributeKey = AttributeKey<Database>("database")

val ApplicationCall.database: Database
	get() = this.attributes[databaseAttributeKey]

suspend inline fun <reified T> ApplicationCall.respondJson(response: ResponseStatus<T>, data: T? = null) {
	data?.let {
		response.data = data
	}
	respondJson(payload = response)
}

/**Réponse rapide en JSON
 * @param [T] Doit être serializable
 * @param [payload] Objet à envoyer en JSON
 * @param [code] Code réponse HTTP (peut être homis si [T] est une [ResponseStatus])
 * @throws RuntimeException si [T] n'est pas serializable
 */
suspend inline fun <reified T> ApplicationCall.respondJson(
	payload: T,
	code: Int = 200,
	serializer: SerializationStrategy<T>? = null
) {
	try {
		val txt = serializer?.let {
			Json.encodeToString(serializer, payload)
		} ?: Json.encodeToString(payload)
		val httpCode = if(payload is ResponseStatus<*>) payload.code else code
		respondText(
			txt,
			ContentType.Application.Json,
			HttpStatusCode.fromValue(httpCode)
		)
	} catch(_: SerializationException) {
		respondText(
			Json.encodeToString(ResponseStatus<Any>("Serialization Error", 500)),
			ContentType.Application.Json,
			HttpStatusCode.InternalServerError
		)
		throw RuntimeException("${T::class.simpleName} is not serializable")
	}
}

/**
 * Récupère le body du call sous forme d'un objet
 * @param [T] Type de l'objet retourner (doit être serializable)
 * @return [T]
 * @throws RuntimeException si [T] n'est pas serializable
 * @throws SerializationException si le body ne peut pas être serializé vers le type [T] fournit, répond 400 à la requête
 */
suspend inline fun <reified T> ApplicationCall.getBodyTyped(): T {
	return try {
		Json.decodeFromString(receiveText())
	} catch(e: SerializationException) {
		this.respondJson(ResponseStatus.BadRequest)
		throw e
	}
}