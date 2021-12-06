fun main() {
    fun part1(input: List<String>): Int {
        val regex = """^(?:[A-Za-z]* ){1,2}(\d+),(\d+) through (\d+),(\d+)${'$'}""".toRegex()
        val lights = mutableMapOf<Pair<Int, Int>, Boolean>()

        input.forEach { line ->
            val result = regex.find(line)
            val (x1, y1, x2, y2) = result!!.destructured.toList().map { it.toInt() }
            when {
                line.startsWith("turn on") -> lights.turnOn(x1, y1, x2, y2)
                line.startsWith("toggle") -> lights.toggle(x1, y1, x2, y2)
                line.startsWith("turn off") -> lights.turnOff(x1, y1, x2, y2)
                else -> throw IllegalStateException()
            }
        }

        return lights.count { it.value }
    }

    fun part2(input: List<String>): Int {
        val regex = """^(?:[A-Za-z]* ){1,2}(\d+),(\d+) through (\d+),(\d+)${'$'}""".toRegex()
        val lights = mutableMapOf<Pair<Int, Int>, Int>()

        input.forEach { line ->
            val result = regex.find(line)
            val (x1, y1, x2, y2) = result!!.destructured.toList().map { it.toInt() }
            when {
                line.startsWith("turn on") -> lights.adjust(x1, y1, x2, y2, 1)
                line.startsWith("toggle") -> lights.adjust(x1, y1, x2, y2, 2)
                line.startsWith("turn off") -> lights.adjust(x1, y1, x2, y2, -1)
                else -> throw IllegalStateException()
            }
        }

        return lights.values.sum()
    }

    // test if implementation meets criteria from the description, like:
    check(part1(listOf("turn on 0,0 through 999,999")) == 1_000_000)
    check(part1(listOf("toggle 0,0 through 999,0")) == 1_000)
    check(part1(listOf("turn off 499,499 through 500,500")) == 0)

    check(part2(listOf("turn on 0,0 through 0,0")) == 1)
    check(part2(listOf("toggle 0,0 through 999,999")) == 2_000_000)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}

private fun MutableMap<Pair<Int, Int>, Int>.adjust(x1: Int, y1: Int, x2: Int, y2: Int, amount: Int) {
    val xRange = if (x1 <= x2) x1..x2 else x1 downTo x2
    val yRange = if (y1 <= y2) y1..y2 else y1 downTo y2
    xRange.forEach { x ->
        yRange.forEach { y ->
            put(Pair(x, y), maxOf(0, getOrPut(Pair(x, y)) { 0 } + amount))
        }
    }
}

private fun MutableMap<Pair<Int, Int>, Boolean>.turnOn(x1: Int, y1: Int, x2: Int, y2: Int) {
    val xRange = if (x1 <= x2) x1..x2 else x1 downTo x2
    val yRange = if (y1 <= y2) y1..y2 else y1 downTo y2
    xRange.forEach { x ->
        yRange.forEach { y ->
            put(Pair(x, y), true)
        }
    }
}

private fun MutableMap<Pair<Int, Int>, Boolean>.turnOff(x1: Int, y1: Int, x2: Int, y2: Int) {
    val xRange = if (x1 <= x2) x1..x2 else x1 downTo x2
    val yRange = if (y1 <= y2) y1..y2 else y1 downTo y2
    xRange.forEach { x ->
        yRange.forEach { y ->
            put(Pair(x, y), false)
        }
    }
}

private fun MutableMap<Pair<Int, Int>, Boolean>.toggle(x1: Int, y1: Int, x2: Int, y2: Int) {
    val xRange = if (x1 <= x2) x1..x2 else x1 downTo x2
    val yRange = if (y1 <= y2) y1..y2 else y1 downTo y2
    xRange.forEach { x ->
        yRange.forEach { y ->
            put(Pair(x, y), !getOrPut(Pair(x, y)) { false })
        }
    }
}
