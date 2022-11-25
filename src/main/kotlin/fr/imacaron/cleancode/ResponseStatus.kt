package fr.imacaron.cleancode

import kotlinx.serialization.Serializable

/**
 * Data class that provide a standard response to request that don't reply data or data of minor importance
 * @author TURBIEZ Denis
 * @param message [String] that describe the HTTP response
 * @param code [Int] that describe the HTTP response
 * @param data Any serializable data to add to the response
 */
@Serializable
data class ResponseStatus<T>(
	val message: String,
	val code: Int,
	var data: T? = null
){
	companion object {
		/**
		 * ```json
		 * {
		 * 	"message": "Bad Request",
		 * 	"code": 400
		 * }
		 * ```
		 * @author TURBIEZ Denis
		 */
		val BadRequest = ResponseStatus<String>("Bad Request", 400)

		/**
		 * ```json
		 * {
		 * 	"message": "Unauthorized",
		 * 	"code": 401
		 * }
		 * ```
		 * @author TURBIEZ Denis
		 */
		val Unauthorized = ResponseStatus<String>("Unauthorized", 401)

		/**
		 * ```json
		 * {
		 * 	"message": "Forbidden",
		 * 	"code": 403
		 * }
		 * ```
		 * @author TURBIEZ Denis
		 */
		val Forbidden = ResponseStatus<String>("Forbidden", 403)

		/**
		 * ```json
		 * {
		 * 	"message": "Not Found",
		 * 	"code": 404
		 * }
		 * ```
		 * @author TURBIEZ Denis
		 */
		val NotFound = ResponseStatus<String>("Not Found", 404)

		/**
		 * ```json
		 * {
		 * 	"message": "Invalid Audience",
		 * 	"code": 403
		 * }
		 * ```
		 * @author TURBIEZ Denis
		 */
		val BadAudience = ResponseStatus<String>("Invalid Audience", 403)

		/**
		 * ```json
		 * {
		 * 	"message": "No Content",
		 * 	"code": 204
		 * }
		 * ```
		 * @author TURBIEZ Denis
		 */
		val NoContent = ResponseStatus<String>("No Content", 204)

		/**
		 * ```json
		 * {
		 * 	"message": "OK",
		 * 	"code": 200
		 * }
		 * ```
		 * @author TURBIEZ Denis
		 */
		val OK = ResponseStatus<String>("OK", 200)

		/**
		 * ```json
		 * {
		 * 	"message": "Internal Server Error",
		 * 	"code": 500
		 * }
		 * ```
		 * @author TURBIEZ Denis
		 */
		val ServerError = ResponseStatus<String>("Internal Server Error", 500)

		/**
		 * ```json
		 * {
		 * 	"message": "Conflict",
		 * 	"code": 409
		 * }
		 * ```
		 * @author TURBIEZ Denis
		 */
		val Conflict = ResponseStatus<String>("Conflict", 409)

		/**
		 * ```json
		 * {
		 * 	"message": "Created",
		 * 	"code": 201
		 * }
		 * ```
		 * @author TURBIEZ Denis
		 */
		val Created = ResponseStatus<String>("Created", 201)
	}
}