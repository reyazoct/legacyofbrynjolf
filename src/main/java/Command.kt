enum class Command(val symbol: Char) {
    UP('u'),
    RIGHT('r'),
    DOWN('d'),
    LEFT('l');

    companion object {
        fun fromSymbol(symbol: Char): Command {
            return values().find { symbol == it.symbol } ?: throw Exception("Invalid Command symbol")
        }
    }
}