class Room(initialState: List<List<Entity>>) {
    private val currentState: List<MutableList<Entity>> = initialState as List<MutableList<Entity>>
    var gameState = GameState.UNDECIDED
        private set

    fun findCoordinates(entity: Entity): List<Coordinate> {
        val coordinates = mutableListOf<Coordinate>()
        currentState.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, column ->
                if (column == entity) {
                    coordinates.add(Coordinate(columnIndex, rowIndex))
                }
            }
        }
        return coordinates
    }

    fun executeCommands(commands: List<Command>) {
        if (gameState != GameState.UNDECIDED) throw Exception("Game Already Completed")
        commands.forEachIndexed { index, command ->
            if (gameState != GameState.UNDECIDED || index >= MAX_MOVES_ALLOWED) return
            executeCommand(command)
        }
    }

    fun executeCommand(command: Command) {
        if (gameState != GameState.UNDECIDED) return
        MOVABLE_ENTITIES.forEach { it.executeCommand(command) }
    }

    private fun Entity.executeCommand(command: Command) {
        findCoordinates(this).forEach {
            var coordinateBeforeCommand = it
            var previousEntity = Entity.EMPTY_SPACE
            while (isNotBlocked(coordinateBeforeCommand, command, this)) {
                val coordinateAfterCommand = coordinateBeforeCommand.move(command)
                previousEntity = changeState(coordinateBeforeCommand, coordinateAfterCommand, this, previousEntity)
                coordinateBeforeCommand = coordinateAfterCommand
            }
        }
    }

    private fun isNotBlocked(coordinate: Coordinate, command: Command, entity: Entity): Boolean {
        val blockers = if (entity == Entity.BRYNJOLF) listOf(Entity.WALL) else listOf(Entity.WALL, Entity.EXIT)
        return when (command) {
            Command.UP -> coordinate.posY > 0 && !blockers.contains(currentState[coordinate.posY - 1][coordinate.posX])
            Command.RIGHT -> coordinate.posX < currentState.first().size - 1 && !blockers.contains(currentState[coordinate.posY][coordinate.posX + 1])
            Command.DOWN -> coordinate.posY < currentState.size - 1 && !blockers.contains(currentState[coordinate.posY + 1][coordinate.posX])
            Command.LEFT -> coordinate.posX > 0 && !blockers.contains(currentState[coordinate.posY][coordinate.posX - 1])
        }
    }

    private fun changeState(coordinateBeforeCommand: Coordinate, coordinateAfterCommand: Coordinate, entity: Entity, previousEntity: Entity): Entity {
        setGameState(coordinateBeforeCommand, coordinateAfterCommand)
        val entityToStore = currentState[coordinateAfterCommand.posY][coordinateAfterCommand.posX]
        currentState[coordinateBeforeCommand.posY][coordinateBeforeCommand.posX] = previousEntity
        currentState[coordinateAfterCommand.posY][coordinateAfterCommand.posX] = entity
        return entityToStore
    }

    private fun setGameState(coordinateBeforeCommand: Coordinate, coordinateAfterCommand: Coordinate) {
        if (gameState != GameState.UNDECIDED) return
        if (currentState[coordinateBeforeCommand.posY][coordinateBeforeCommand.posX] == Entity.GUARD &&
                currentState[coordinateAfterCommand.posY][coordinateAfterCommand.posX] == Entity.BRYNJOLF) {
            gameState = GameState.LOSE
        } else if (currentState[coordinateBeforeCommand.posY][coordinateBeforeCommand.posX] == Entity.BRYNJOLF &&
                currentState[coordinateAfterCommand.posY][coordinateAfterCommand.posX] == Entity.EXIT) {
            gameState = GameState.WIN
        }
    }

    fun commandIfBrynjolfAndExitInSameLineWithoutBlocker(): Command? {
        if (gameState != GameState.UNDECIDED) return null
        val brynjolfCoordinate = findCoordinates(Entity.BRYNJOLF).first()
        val exitCoordinate = findCoordinates(Entity.EXIT).first()
        val isInSameX = brynjolfCoordinate.posX == exitCoordinate.posX
        if (isInSameX) return commandIfNoBlocker(brynjolfCoordinate.posX, brynjolfCoordinate.posY, exitCoordinate.posY)
        val isInSameY = brynjolfCoordinate.posY == exitCoordinate.posY
        if (isInSameY) return commandIfNoBlocker(brynjolfCoordinate.posY, brynjolfCoordinate.posX, exitCoordinate.posX, true)
        return null
    }

    private fun commandIfNoBlocker(samePosition: Int, differentPositionOne: Int, differentPositionTwo: Int, invert: Boolean = false): Command? {
        val startPosition = if (differentPositionOne > differentPositionTwo) differentPositionTwo else differentPositionOne
        val endPosition = if (startPosition == differentPositionOne) differentPositionTwo else differentPositionOne
        var isBlocker = false
        for (position in startPosition + 1 until endPosition) {
            if (currentState[if (invert) position else samePosition][if (invert) samePosition else position] != Entity.EMPTY_SPACE) {
                isBlocker = true
                break
            }
        }
        if (isBlocker) return null
        return when {
            differentPositionOne < differentPositionTwo && invert -> Command.RIGHT
            differentPositionTwo < differentPositionOne && invert -> Command.LEFT
            differentPositionOne < differentPositionTwo && !invert -> Command.DOWN
            differentPositionTwo < differentPositionOne && !invert -> Command.UP
            else -> null
        }
    }

    fun displayCurrentState() {
        currentState.forEach { eachRow ->
            println(eachRow.map { it.symbol }.joinToString(DELIMITER))
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Room) return super.equals(other)
        return currentState.toTypedArray().contentEquals(other.currentState.toTypedArray())
    }

    override fun hashCode(): Int {
        return currentState.hashCode()
    }

    companion object {
        private val MOVABLE_ENTITIES = listOf(Entity.BRYNJOLF, Entity.GUARD)
        private const val MAX_MOVES_ALLOWED = 4
    }
}
