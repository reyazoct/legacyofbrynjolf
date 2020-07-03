import java.io.BufferedReader
import java.io.FileReader
import java.lang.Exception

fun main(args: Array<String>) {
    if (args.isEmpty()) throw Exception("Commands not found")
    val room = readFile()
    room.executeCommands(args.first().toCharArray().map { Command.fromSymbol(it) })
    room.displayCurrentState()
    println(room.gameState)
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