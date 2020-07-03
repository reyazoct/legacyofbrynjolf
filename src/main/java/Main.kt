import java.io.BufferedReader
import java.io.FileReader
import java.lang.Exception

fun main(args: Array<String>) {
    val room = readFile()
    val simulator = Simulator(room, args.firstOrNull()?.toCharArray()?.map { Command.fromSymbol(it) })
    simulator.simulate()
    println(simulator.getRemainingMoves())
    println(simulator.getRoomGameState())
}

fun readFile(): Room {
    val fileReader = FileReader("room.txt")
    val bufferReader = BufferedReader(fileReader)
    val columnList = mutableListOf<List<Entity>>()
    while (true) {
        val dataLine = bufferReader.readLine()
        if (dataLine.isNullOrBlank()) break
        val rowList = dataLine.split(DELIMITER).map { Entity.fromSymbol(it.single()) }
        columnList.add(rowList)
    }
    return Room(columnList)
}

const val DELIMITER = ","