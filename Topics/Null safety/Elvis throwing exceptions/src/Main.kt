    fun main() {
        val line = readlnOrNull() // you need to add something
        println("Elvis says: ${line ?: throw IllegalStateException()}" )
    }