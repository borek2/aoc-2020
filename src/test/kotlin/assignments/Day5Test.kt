package assignments

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day5Test {

    private val input = this::class.java.classLoader.getResourceAsStream("day5")?.bufferedReader()?.useLines {
        it.toList()
    }.orEmpty()

    @Test
    fun calculateFirst() {
        val expected = 357
        assertEquals(expected, Day5.runFirst(input))
    }
}