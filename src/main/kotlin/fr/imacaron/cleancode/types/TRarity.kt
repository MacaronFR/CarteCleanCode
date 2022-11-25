package fr.imacaron.cleancode.types

import kotlinx.serialization.Serializable

@Serializable
enum class TRarity(override val value: String): TEnum<String> {
	Common("common"),
	Rare("rare"),
	Legendary("legendary");

	override fun getValue(value: String): TEnum<String> = values().first { it.value == value }
}
