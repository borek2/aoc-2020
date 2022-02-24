package assignments

object Day5 {

    fun runFirst(input: List<String> = input()): Int = calculateFirst()

    private fun calculateFirst(): Int {
        return 0
    }

    private fun input(): List<String> {
        return this::class.java.classLoader.getResourceAsStream("day5")?.bufferedReader()?.useLines {
            it.toList()
        }.orEmpty()
    }
}