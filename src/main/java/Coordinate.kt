data class Coordinate(var posX: Int, var posY: Int) {
    override fun equals(other: Any?): Boolean {
        if (other !is Coordinate) return super.equals(other)
        return posX == other.posX && posY == other.posY
    }

    fun move(command: Command): Coordinate {
        return when (command) {
            Command.UP -> Coordinate(posX, posY - 1)
            Command.RIGHT -> Coordinate(posX + 1, posY)
            Command.DOWN -> Coordinate(posX, posY + 1)
            Command.LEFT -> Coordinate(posX - 1, posY)
        }
    }

    override fun hashCode(): Int {
        return posX.hashCode() * posY.hashCode()
    }
}