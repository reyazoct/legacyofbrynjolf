class Room(initialState: List<List<Entity>>) {
    private val currentState = initialState

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

    private fun displayCurrentState() {
        currentState.forEach { eachRow ->
            println(eachRow.map { it.symbol }.joinToString(DELIMITER))
        }
    }
}