package assignments

import kotlin.math.ceil

object Day5 {

    fun runFirst(input: List<String> = input()) = input
        .maxOf { it.calculateId() }

    fun runSecond(input: List<String> = input()) = input
        .asSequence()
        .map { it.calculateId() }
        .sorted()
        .windowed(2)
        .find { window -> window[1] - window[0] > 1 }
        ?.first()
        ?.plus(1)

    private fun String.calculateId() = calculateRow() * 8 + calculateColumn()

    private fun String.calculateRow() = filter { it == 'F' || it == 'B' }.calculate(0..127, 'F')

    private fun String.calculateColumn() = filter { it == 'R' || it == 'L' }.calculate(0..7, 'L')

    private fun String.calculate(rangeLeft: IntRange, lowerChar: Char): Int =
        if (isEmpty()) {
            rangeLeft.last
        } else {
            takeLast(length - 1)
                .calculate(
                    if (first() == lowerChar) rangeLeft.first..rangeLeft.center() else rangeLeft.center()..rangeLeft.last,
                    lowerChar
                )
        }

    private fun IntRange.center(): Int = last - ceil((last - first) / 2.0).toInt()

    private fun input(): List<String> {
        return this::class.java.classLoader.getResourceAsStream("day5")?.bufferedReader()?.useLines {
            it.toList()
        }.orEmpty()
    }
}