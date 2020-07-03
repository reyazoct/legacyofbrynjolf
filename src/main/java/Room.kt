class Room(initialState: List<List<Entity>>) {
    private val currentState: List<MutableList<Entity>> = initialState as List<MutableList<Entity>>

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

    fun executeCommand(command: Command) {
        MOVE_ABLE_ENTITIES.forEach { entity ->
            entity.executeCommand(command)
        }
    }

    private fun Entity.executeCommand(command: Command) {
        findCoordinates(this).forEach {
            var coordinateBeforeCommand = it
            while (isNotBlocked(coordinateBeforeCommand, command, this)) {
                val coordinateAfterCommand = coordinateBeforeCommand.move(command)
                changeState(coordinateBeforeCommand, coordinateAfterCommand, this)
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

    private fun changeState(coordinateBeforeCommand: Coordinate, coordinateAfterCommand: Coordinate, entity: Entity) {
        currentState[coordinateBeforeCommand.posY][coordinateBeforeCommand.posX] = Entity.EMPTY_SPACE
        currentState[coordinateAfterCommand.posY][coordinateAfterCommand.posX] = entity
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
        private val MOVE_ABLE_ENTITIES = listOf(Entity.BRYNJOLF, Entity.GUARD)
    }
}
