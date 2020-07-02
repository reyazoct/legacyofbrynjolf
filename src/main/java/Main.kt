import java.io.BufferedReader
import java.io.FileReader
import java.lang.Exception

fun main(args: Array<String>) {
    if (args.isEmpty()) throw Exception("Arguments not found")
    readFile(args[0])
}

fun readFile(fileName: String) {
    val fileReader = FileReader(fileName)
    val bufferReader = BufferedReader(fileReader)
    while (true) {
        val dataLine = bufferReader.readLine()
        if (dataLine.isNullOrBlank()) break;
        println(dataLine)
    }
}
