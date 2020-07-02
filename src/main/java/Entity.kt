import java.lang.Exception

enum class Entity(val symbol: Char) {
    EMPTY_SPACE('.'),
    GUARD('g'),
    EXIT('o'),
    BRYNJOLF('b'),
    WALL('x');

    companion object {
        fun fromSymbol(symbol: Char): Entity {
            return values().find { it.symbol == symbol } ?: throw Exception("Invalid Entity Symbol")
        }
    }
}