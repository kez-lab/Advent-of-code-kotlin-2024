import kotlin.math.abs

val WHITESPACE_REGEX = Regex("\\s+")

fun main() {
    fun part1(input: List<String>): Int {
        val (leftList, rightList) = input
            .map { distinctLine ->
                val (left, right) = distinctLine.split(WHITESPACE_REGEX).map { it.toInt() }
                Pair(left, right)
            }.unzip()

        val sortedLeft = leftList.sorted()
        val sortedRight = rightList.sorted()

        return sortedLeft.zip(sortedRight) { left, right ->
            abs(left - right)
        }.sum()
    }

    fun part2(input: List<String>): Int {
        var score = 0
        val (leftList, rightList) = input
            .map { distinctLine ->
                val (left, right) = distinctLine.split(WHITESPACE_REGEX).map { it.toInt() }
                Pair(left, right)
            }.unzip()
        val checkMap = mutableMapOf<Int, Int>()
        rightList.forEach {
            checkMap.put(it, (checkMap.getOrDefault(it, 0) + 1))
        }

        leftList.forEach {
            val count = checkMap.get(it) ?: return@forEach
            val addScore = count * it
            score += addScore
        }

        return score
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
