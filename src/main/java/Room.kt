class Room(initialState: List<List<Entity>>) {
    private val currentState: List<MutableList<Entity>> = initialState as List<MutableList<Entity>>

    fun findCoordinates(entity: Entity): List<Coordinate> {
        val coordinates = mutableListOf<Coordinate>()
        currentState.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, column ->
                if (column == entity) {
                    coordinates.add(Coordinate(rowIndex, columnIndex))
                }
            }
        }
        return coordinates
    }

    fun executeCommand(command: Command) {
        MOVE_ABLE_ENTITIES.forEach { entity ->
            entity.executeCommand(command)
        }
    }

    private fun Entity.executeCommand(command: Command) {
        findCoordinates(this).forEach {
            if (isBlocked(it, command)) return@forEach
            val coordinateAfterCommand = it.move(command)
            changeState(it, coordinateAfterCommand, this)
        }
    }

    private fun isBlocked(coordinate: Coordinate, command: Command): Boolean {
        return when (command) {
            Command.UP -> coordinate.posY <= 0
            Command.RIGHT -> coordinate.posX >= currentState.first().size - 1
            Command.DOWN -> coordinate.posY >= currentState.size - 1
            Command.LEFT -> coordinate.posX <= 0
        }
    }

    private fun changeState(coordinateBeforeCommand: Coordinate, coordinateAfterCommand: Coordinate, entity: Entity) {
        currentState[coordinateBeforeCommand.posX][coordinateBeforeCommand.posY] = Entity.EMPTY_SPACE
        currentState[coordinateAfterCommand.posX][coordinateAfterCommand.posY] = entity
    }


    private fun displayCurrentState() {
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
        private val MOVE_ABLE_ENTITIES = listOf<Entity>(Entity.BRYNJOLF, Entity.GUARD)
    }
}
