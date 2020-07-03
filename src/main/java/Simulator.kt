class Simulator(private val room: Room, moves: List<Command>? = null) {
    private val remainingMoves = mutableListOf<Command>()

    init {
        moves?.let { room.executeCommands(it) }
    }

    fun getRemainingMoves(): List<Command> {
        return remainingMoves
    }

    fun simulate() {
        if (room.gameState != GameState.UNDECIDED) return
        if (checkBrynjolfAndExitInSameLineWithoutBlocker()) return
    }

    private fun checkBrynjolfAndExitInSameLineWithoutBlocker(): Boolean {
        room.commandIfBrynjolfAndExitInSameLineWithoutBlocker()?.let {
            remainingMoves.add(it)
            return true
        } ?: run { return false }
    }
}