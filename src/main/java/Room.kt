class Room(private val currentState: List<List<Entity>>) {
    private fun displayCurrentState() {
        currentState.forEach { eachRow ->
            println(eachRow.map { it.symbol }.joinToString(DELIMITER))
        }
    }
}