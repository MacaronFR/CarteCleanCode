package fr.imacaron.cleancode.db.entity

import org.ktorm.entity.Entity

interface SpecialityEntity: Entity<SpecialityEntity> {
	val id: Int
	val name: String
	val health: Int
	val power: Int
	val armor: Int
	val bonuPower: Int
	val bonusAgainst: SpecialityEntity
}
