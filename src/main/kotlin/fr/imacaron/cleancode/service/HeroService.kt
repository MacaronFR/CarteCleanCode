package fr.imacaron.cleancode.service

import fr.imacaron.cleancode.types.THero

internal interface HeroService {
	fun get(id: Int): THero

	fun create(hero: THero)

	fun update(id: Int, updater: THero.() -> Unit)

	fun delete(id: Int): THero

	fun search()
}