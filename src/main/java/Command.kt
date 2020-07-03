enum class Command(val symbol: Char) {
    UP('u'),
    RIGHT('r'),
    DOWN('d'),
    LEFT('l');

    fun getOppositeCommand(): Command {
        return when (this) {
            UP -> DOWN
            RIGHT -> LEFT
            DOWN -> UP
            LEFT -> RIGHT
        }
    }

    companion object {
        fun fromSymbol(symbol: Char): Command {
            return values().find { symbol == it.symbol } ?: throw Exception("Invalid Command symbol")
        }
    }
}