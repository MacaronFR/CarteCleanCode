package fr.imacaron.cleancode.serializer.enums.string

import fr.imacaron.cleancode.types.TEnum
import org.ktorm.schema.BaseTable
import org.ktorm.schema.Column
import org.ktorm.schema.SqlType
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Types

/**
 * This class is a [SqlType] extension with the goal of using kotlin enum with a value field that is not the name of the enum instance, actually an [String] (for example with
 * ```kotlin
 * enum class Toto (foo: String){
 * 	Name("Toto");
 *}
 *```
 * have the value stored and get as `56` instead of the default `"Name"`
 * @author Denis TURBIEZ
 * @param C The enum class we want to store
 * @param enumClass The class of the enum to store as an [String]
 * @see enumString
 */
class EnumStringType<C: TEnum<String>>(private val enumClass: Class<C>) : SqlType<C>(Types.VARCHAR, "varchar") {

	/**
	 * The method to get the instance of [C] enum with an [String] value
	 * @author Denis TURBIEZ
	 */
	private val method= enumClass.getDeclaredMethod("getValue", String::class.java).also { it.isAccessible = true }

	/**
	 * @author Denis TURBIEZ
	 */
	override fun doSetParameter(ps: PreparedStatement, index: Int, parameter: C) {
		ps.setString(index, parameter.value)
	}

	/**
	 * @author Denis TURBIEZ
	 */
	override fun doGetResult(rs: ResultSet, index: Int): C? {
		return rs.getString(index).let { enumClass.cast(method(enumClass.enumConstants.first(), it)) }
	}
}

/**
 * The function to use in [BaseTable] declaration to have an enum [C] that will be stored as [String]
 * @author Denis TURBIEZ
 * @see fr.kamae.api.bdd.Users
 * @param name The name of the field in the database
 */
inline fun <reified C : TEnum<String>> BaseTable<*>.enumString(name: String): Column<C> {
	return registerColumn(name, EnumStringType(C::class.java))
}