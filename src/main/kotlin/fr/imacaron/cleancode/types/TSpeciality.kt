package fr.imacaron.cleancode.types

import kotlinx.serialization.Serializable

@Serializable
data class TSpeciality(
	val name: String,
	val health: Int,
	val power: Int,
	val armor: Int,
	val bonus: Pair<Int, TSpeciality>
)