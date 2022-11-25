package fr.imacaron.cleancode.db.entity

import fr.imacaron.cleancode.types.TRarity
import fr.imacaron.cleancode.types.TSpeciality
import org.ktorm.entity.Entity

interface HeroEntity: Entity<HeroEntity> {
	var id: Int
	var name: String
	var health: Int
	var xp: Int
	var power: Int
	var armor: Int
	var speciality: SpecialityEntity
	var rarity: TRarity
	var level: Int

	companion object: Entity.Factory<HeroEntity>()
}