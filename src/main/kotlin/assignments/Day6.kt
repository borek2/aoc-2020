package assignments

object Day6 {

    fun part1(input: List<String> = input()): Int = answeredYes(input)

    fun part2(input: List<String> = input()): Int = allAnsweredYes(input)

    private fun answeredYes(rest: List<String>, answeredYes: Set<Char> = emptySet()): Int =
        when (val first = rest.firstOrNull()) {
            null -> answeredYes.size
            "" -> answeredYes.size + answeredYes(rest.minus(first))
            else -> answeredYes(rest.minus(first), answeredYes.plus(first.toList()))
        }

    private fun allAnsweredYes(rest: List<String>, allAnsweredYes: List<Char> = rest.first().toList()): Int =
        when (val first = rest.firstOrNull()) {
            null -> allAnsweredYes.size
            "" -> allAnsweredYes.size + allAnsweredYes(rest.minus(first))
            else -> allAnsweredYes(rest.minus(first), allAnsweredYes.filter { first.contains(it) })
        }

    private fun input(): List<String> {
        return this::class.java.classLoader.getResourceAsStream("day6")?.bufferedReader()?.useLines {
            it.toList()
        }.orEmpty()
    }
}