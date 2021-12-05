fun main() {
    fun part1(input: String): Int {
        val sequence = generateSequence(1) { it + 1 }
        sequence.forEach { i ->
            if ((input + i).md5().startsWith("00000")) return i
        }
        throw IllegalStateException()
    }

    fun part2(input: String): Int {
        val sequence = generateSequence(1) { it + 1 }
        sequence.forEach { i ->
            if ((input + i).md5().startsWith("000000")) return i
        }
        throw IllegalStateException()
    }

    // test if implementation meets criteria from the description, like:
    check(part1("abcdef") == 609043)
    check(part1("pqrstuv") == 1048970)

    println(part1("ckczppom"))
    println(part2("ckczppom"))
}
