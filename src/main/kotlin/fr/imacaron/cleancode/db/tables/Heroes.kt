package fr.imacaron.cleancode.db.tables

import fr.imacaron.cleancode.db.entity.HeroEntity
import fr.imacaron.cleancode.serializer.enums.string.enumString
import fr.imacaron.cleancode.types.TRarity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object Heroes: Table<HeroEntity>("HEROES"){
	val id = int("id_hero").primaryKey().bindTo { it.id }
	val name = varchar("namme").bindTo { it.name }
	val health = int("health").bindTo { it.health }
	val xp = int("xp").bindTo { it.xp }
	val power = int("power").bindTo { it.power }
	val armor = int("armor").bindTo { it.armor }
	val speciality = int("speciality").references(Speciality) { it.speciality }
	val rarity = enumString<TRarity>("rarity").bindTo { it.rarity }
	var level = int("level").bindTo { it.level }
}
