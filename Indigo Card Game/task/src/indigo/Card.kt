package indigo

data class Card(val rank: String, val suit: String) {
    override fun toString(): String = "$rank$suit"

}