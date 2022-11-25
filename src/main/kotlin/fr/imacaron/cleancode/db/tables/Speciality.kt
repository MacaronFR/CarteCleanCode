package fr.imacaron.cleancode.db.tables

import fr.imacaron.cleancode.db.entity.SpecialityEntity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object Speciality: Table<SpecialityEntity>("SPECIALITY") {
	val id = int("id_speciality").primaryKey()
	val name = varchar("name")
	val health = int("health")
	val power = int("power")
	val armor = int("armor")
	val bonus_power = int("bonus_power")
	val bonus_against = int("bonus_against").references(Speciality){ it }
}
