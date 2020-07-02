data class Coordinate(val posX: Int, val posY: Int) {
    override fun equals(other: Any?): Boolean {
        if (other !is Coordinate) return super.equals(other)
        return posX == other.posX && posY == other.posY
    }
}