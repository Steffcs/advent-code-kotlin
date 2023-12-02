import java.io.File
import kotlin.math.max

class Day02(private val input: List<String>) {
    private val maxCubes = mapOf("red" to 12, "green" to 13, "blue" to 14)
    fun solvePartOne(): Int {

        return input.sumOf { line ->
            val parts = line.split(": ", ", ", "; ")
            val gameId = parts.first().filter { it.isDigit() }.toInt()
            val sets = parts.drop(1)

            if (sets.all { checkSet(it, maxCubes) }) gameId else 0
        }
    }

    fun solvePartTwo(): Int {
        return input.map { line ->
            val game = parseGame(line)
            val minCubes = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
            game.forEach { set ->
                set.forEach { (color, number) ->
                    minCubes[color] = max(minCubes[color] ?: 0, number)
                }
            }
            minCubes.values.fold(1) { acc, value -> acc * value }
        }.sum()
    }

    private fun checkSet(set: String, maxCubes: Map<String, Int>): Boolean {
        val cubes = set.split(" ").windowed(2, 2, false) {
            it.last() to it.first().toInt()
        }.toMap()

        return cubes.all { (color, count) -> count <= (maxCubes[color] ?: 0) }
    }

    private fun parseGame(line: String): List<Map<String, Int>> {
        val parts = line.split(": ", ", ", "; ")
        return parts.drop(1).map { set ->
            set.split(" ").windowed(2, 2, false).associate {
                val (color, number) = it.last() to it.first().toInt()
                color to number
            }
        }
    }
}

fun main() {
    val fileName = "src/input/day2.txt"
    val lines = File(fileName).readLines()
    val sum = Day02(lines)
    println("Sum of the IDs of possible games: ${sum.solvePartOne()}")
    println("Sum of the power of the sets: ${sum.solvePartTwo()}")
}