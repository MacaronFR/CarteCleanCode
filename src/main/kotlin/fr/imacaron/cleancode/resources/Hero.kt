package fr.imacaron.cleancode.resources

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/hero")
class Hero{
	@Serializable
	@Resource("{id}")
	class Id(val parent: Hero, val id: Int)
}