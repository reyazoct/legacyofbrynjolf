data class Coordinate(var posX: Int, var posY: Int) {
    override fun equals(other: Any?): Boolean {
        if (other !is Coordinate) return super.equals(other)
        return posX == other.posX && posY == other.posY
    }

    fun move(command: Command) {
        when (command) {
            Command.UP -> posX -= 1
            Command.RIGHT -> posY += 1
            Command.DOWN -> posX += 1
            Command.LEFT -> posY -= 1
        }
    }

    override fun hashCode(): Int {
        return posX.hashCode() * posY.hashCode()
    }
}