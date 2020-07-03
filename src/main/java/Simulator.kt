class Simulator(private val room: Room, moves: List<Command>? = null) {
    private val remainingMoves = mutableListOf<Command>()

    init {
        moves?.let { room.executeCommands(it) }
    }

    fun getRemainingMoves(): List<Command> {
        return remainingMoves
    }

    fun simulate() {
        simulate(room)?.let {
            room.executeCommands(it)
            remainingMoves.addAll(it)
        }
    }

    fun getRoomGameState(): GameState {
        return room.gameState
    }

    private fun simulate(room: Room, executedMoves: MutableList<Command> = mutableListOf()): List<Command>? {
        if (room.gameState != GameState.UNDECIDED) return executedMoves.toList()
        room.commandIfBrynjolfAndExitInSameLineWithoutBlocker()?.let {
            executedMoves.add(it)
            return executedMoves.toList()
        }
        val possibleMoves = room.getPossibleMoves()
        if (possibleMoves.isEmpty()) return null
        var verifiedMoves: List<Command>? = null
        possibleMoves.forEach {
            if (isRepeatingSequence(executedMoves, it)) return@forEach
            val newRoom = room.executeCommandAsNewRoom(it)
            val temp = executedMoves.toMutableList()
            temp.add(it)
            val simulatedMoves = simulate(newRoom, temp.toMutableList())
            if (simulatedMoves.isNullOrEmpty()) return@forEach
            if (verifiedMoves == null || simulatedMoves.size < verifiedMoves!!.size) verifiedMoves = simulatedMoves
        }
        return verifiedMoves
    }

    private fun isRepeatingSequence(commands: List<Command>, command: Command): Boolean {
        if (commands.isEmpty()) return false
        return commands[commands.size - 1] == command || commands[commands.size - 1] == command.getOppositeCommand()
    }
}