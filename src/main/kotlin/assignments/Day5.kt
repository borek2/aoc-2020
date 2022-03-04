package assignments

import kotlin.math.ceil

object Day5 {

    fun runFirst(input: List<String> = input()): Int = calculateFirst(input)

    private fun calculateFirst(input: List<String>): Int = input.maxOf { it.calculateRow() * 8 + it.calculateColumn() }

    private fun String.calculateRow() = filter { it == 'F' || it == 'B' }.calculate(0..127, 'F')

    private fun String.calculateColumn() = filter { it == 'R' || it == 'L' }.calculate(0..7, 'L')

    private fun String.calculate(rangeLeft: IntRange, lowerChar: Char): Int =
        if (rangeLeft.toList().size == 2) {
            rangeLeft.last
        } else {
            takeLast(length - 1)
                .calculate(
                    if (first() == lowerChar) rangeLeft.first..rangeLeft.halfOfRange() else rangeLeft.halfOfRange()..rangeLeft.last,
                    lowerChar
                )
        }

    private fun IntRange.halfOfRange(): Int = last - ceil((last - first) / 2.0).toInt()

    private fun input(): List<String> {
        return this::class.java.classLoader.getResourceAsStream("day5")?.bufferedReader()?.useLines {
            it.toList()
        }.orEmpty()
    }
}