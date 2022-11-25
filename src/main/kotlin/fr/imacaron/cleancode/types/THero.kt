package fr.imacaron.cleancode.types

import kotlinx.serialization.Serializable

@Serializable
data class THero(
	var name: String,
	var health: Int,
	var xp: Int,
	var power: Int,
	var armor: Int,
	var speciality: TSpeciality,
	var rarity: TRarity,
	var level: Int
)
