fun main() {
    fun part1(input: String): Int {
        val houses = mutableMapOf(Pair(0, 0) to true)
        var x = 0
        var y = 0
        input.forEach {
            when (it) {
                '^' -> y++
                'v' -> y--
                '>' -> x++
                '<' -> x--
                else -> throw IllegalStateException()
            }
            houses[Pair(x, y)] = true
        }
        return houses.count { it.value }
    }

    fun part2(input: String): Int {
        val houses = mutableMapOf(Pair(0, 0) to true)
        var x = 0
        var y = 0
        val santa = input.filterIndexed { index, _ -> index % 2 == 1 }
        val robot = input.filterIndexed { index, _ -> index % 2 == 0 }
        santa.forEach {
            when (it) {
                '^' -> y++
                'v' -> y--
                '>' -> x++
                '<' -> x--
                else -> throw IllegalStateException()
            }
            houses[Pair(x, y)] = true
        }

        x = 0
        y = 0
        robot.forEach {
            when (it) {
                '^' -> y++
                'v' -> y--
                '>' -> x++
                '<' -> x--
                else -> throw IllegalStateException()
            }
            houses[Pair(x, y)] = true
        }
        return houses.count { it.value }
    }

    // test if implementation meets criteria from the description, like:
    check(part1(">") == 2)
    check(part1("^>v<") == 4)

    check(part2("^v") == 3)
    check(part2("^>v<") == 3)
    check(part2("^v^v^v^v^v") == 11)

    val input = readInput("Day03").first()
    println(part1(input))
    println(part2(input))
}
