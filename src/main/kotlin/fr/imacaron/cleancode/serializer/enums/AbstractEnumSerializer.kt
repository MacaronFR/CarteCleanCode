package fr.imacaron.cleancode.serializer.enums

import fr.imacaron.cleancode.types.TEnum
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.jvm.isAccessible

/**
 * An abstract serializer that need 2 type, a [Class] object *(get it with YouClass::class.java)* and the primitive kind serialized.
 * We recommend to implement an abstract serializer that inherit this one for every **type** needed.
 * By doing that you have to implement only once [decodeValue] and [serialize] for every enum with that type
 * @author Denis TURBIEZ
 * @param C The enum we want to serialize
 * @param T The value the enum turn into
 * @param enumClass The class object of type [C]
 * @param type The [PrimitiveKind] of [T]
 * @see fr.kamae.api.serializer.enums.integer.AbstractEnumIntSerializer
 */
abstract class AbstractEnumSerializer<C: TEnum<T>, T>(private val enumClass: KClass<C>, type: PrimitiveKind): KSerializer<C> {

	/**
	 * The descriptor of the serializer, take the *simpleName* of the class and the **type** given
	 * @author Denis TURBIEZ
	 */
	final override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("${enumClass.simpleName}Serializer", type)

	/**
	 * The reflected method to get values of the enum class
	 * @author Denis TURBIEZ
	 */
	private val valuesMethod = enumClass.declaredFunctions.find { it.name == "values" }!!.also { it.isAccessible = true }

	/**
	 * Calling the values() method of the enum class
	 * @author Denis TURBIEZ
	 * @return All the instance of the enum class
	 */
	private fun useValues(): Array<C> = valuesMethod.call() as Array<C>

	/**
	 * Deserialize the value of type [T] into the enum [C]
	 * Used directly by the **kotlinx.serialization** plugin
	 * @author Denis TURBIEZ
	 * @param decoder The [Decoder] that old the value we want to deserialize
	 * @return The enum that correspond to the value decoded
	 * @throws SerializationException If the value decoded isn't a valid one
	 */
	final override fun deserialize(decoder: Decoder): C {
		val value = decodeValue(decoder)
		return useValues().firstOrNull{ it.value == value } ?: throw SerializationException("Unknown ${enumClass.simpleName} $value")
	}

	/**
	 * The function that decode the value given into the right type [T]
	 * @author Denis TURBIEZ
	 * @param decoder The decoder to use
	 * @return The value decoded of type [T]
	 */
	abstract fun decodeValue(decoder: Decoder): T
}