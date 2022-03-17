package assignments

object Day6 {

    fun calculateFirst(input: List<String> = input()): Int = calculate1(input)

    fun calculateSecond(input: List<String> = input()): Int = calculate2(input)

    private fun calculate1(rest: List<String>, answeredYes: Set<Char> = emptySet()): Int = when (val first = rest.firstOrNull()) {
        null -> answeredYes.size
        "" -> answeredYes.size + calculate1(rest.minus(first))
        else -> calculate1(rest.minus(first), answeredYes.plus(first.map { it }))
    }

    private fun calculate2(rest: List<String>, answeredYes: Set<Char> = rest.first().map { it }.toSet()): Int = when (val first = rest.firstOrNull()) {
        null -> answeredYes.size
        "" -> answeredYes.size + calculate2(rest.minus(first))
        else -> calculate2(rest.minus(first), answeredYes.filter { first.contains(it) }.toSet())
    }

    private fun input(): List<String> {
        return this::class.java.classLoader.getResourceAsStream("day6")?.bufferedReader()?.useLines {
            it.toList()
        }.orEmpty()
    }
}