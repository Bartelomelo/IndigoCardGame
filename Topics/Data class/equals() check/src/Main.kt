data class Client(val name: String, val age: Int, val balance: Int) {
    override fun equals(other: Any?): Boolean {
        other as Client
        return name == other.name && age == other.age
    }
}

fun main() {
    var name = readln()
    var age = readln().toInt()
    var balance = readln().toInt()
    val client1 = Client(name, age, balance)
    name = readln()
    age = readln().toInt()
    balance = readln().toInt()
    val client2 = Client(name, age, balance)
    println(client1.equals(client2))
}
