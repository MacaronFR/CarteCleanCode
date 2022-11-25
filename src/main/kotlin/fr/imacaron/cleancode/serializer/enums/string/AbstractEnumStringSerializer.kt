package fr.imacaron.cleancode.serializer.enums.string

import fr.imacaron.cleancode.serializer.enums.AbstractEnumSerializer
import fr.imacaron.cleancode.types.TEnum
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.reflect.KClass

/**
 * Implement [AbstractEnumSerializer] with a string parameters
 * To use, simply create an **object** that extend this class with the enum type as type parameter
 * @author Denis TURBIEZ
 * @param C The type of the enum that we want to serialize
 * @see StatusSerializer
 * @see ScopeSerializer
 */
abstract class AbstractEnumStringSerializer<C: TEnum<String>>(enumClass: KClass<C>): AbstractEnumSerializer<C, String>(enumClass, PrimitiveKind.STRING) {

	/**
	 * Decode the value into [String]
	 * @author Denis TURBIEZ
	 */
	override fun decodeValue(decoder: Decoder): String = decoder.decodeString()

	/**
	 * Serialize the value of type [C] into an [String]
	 * @param encoder The [Encoder] to encode [value] to [String]
	 * @param value The [C] enum instance to encode into [String]
	 * @author Denis TURBIEZ
	 */
	override fun serialize(encoder: Encoder, value: C) {
		encoder.encodeString(value.value)
	}
}