fun main() {
    fun part1(input: String): Int {
        var floor = 0
        input.forEach {
            when (it) {
                '(' -> floor++
                ')' -> floor--
                else -> throw IllegalArgumentException()
            }
        }
        return floor
    }

    fun part2(input: String): Int {
        var floor = 0
        input.forEachIndexed { index, c ->
            when (c) {
                '(' -> floor++
                ')' -> floor--
                else -> throw IllegalArgumentException()
            }
            if (floor == -1) return index + 1
        }
        throw IllegalStateException()
    }

    // test if implementation meets criteria from the description, like:
    check(part1("(())") == 0)
    check(part1("()()") == 0)
    check(part1("(((") == 3)
    check(part1("(()(()(") == 3)
    check(part1("))(((((") == 3)
    check(part1("())") == -1)
    check(part1("))(") == -1)
    check(part1(")))") == -3)
    check(part1(")())())") == -3)

    check(part2(")") == 1)
    check(part2("()())") == 5)

    val input = readInput("Day01").first()
    println(part1(input))
    println(part2(input))
}
