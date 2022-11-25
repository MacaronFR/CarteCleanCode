package fr.imacaron.cleancode.repository

import fr.imacaron.cleancode.db.entity.HeroEntity

interface HeroRepository {
	fun get(id: Int): HeroEntity
}