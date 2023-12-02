import java.io.File

class Day01(private val input: List<String>) {
    private val numberWords = mapOf(
            "one" to "1", "two" to "2", "three" to "3", "four" to "4",
            "five" to "5", "six" to "6", "seven" to "7", "eight" to "8", "nine" to "9"
    )

    fun solve(): Int = input.sumOf {
        extractDigitsAndWords(it)
    }

    private fun extractDigitsAndWords(line: String): Int {
        val digitList = mutableListOf<String>()
        var index = 0
        while (index < line.length) {
            var foundWord = false
            for (word in numberWords.keys) {
                if (line.startsWith(word, index)) {
                    digitList.add(numberWords[word]!!)
                    index += word.length
                    foundWord = true
                    break
                }
            }
            if (!foundWord) {
                if (line[index].isDigit()) {
                    digitList.add(line[index].toString())
                }
                index++
            }
        }

        return when {
            digitList.isEmpty() -> 0
            digitList.size == 1 -> (digitList.first() + digitList.first()).toInt()
            else -> (digitList.first() + digitList.last()).toInt()
        }
    }
}

fun main() {
    val fileName = "src/input/day1.txt"
    val lines = File(fileName).readLines()
    val sum = Day01(lines)
    println("Total calibration value part one: ${sum.solve()}")
}