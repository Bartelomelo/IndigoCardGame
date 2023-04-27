package indigo

class Deck {
    val cardStack = mutableListOf<Card>()

    fun buildDeck() {
        val ranks = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
        val suits = listOf("♠", "♥", "♦", "♣")
        for (suit in suits) {
            for (rank in ranks) {
                cardStack.add(Card(rank, suit))
            }
        }
        cardStack.shuffle()
    }


    fun shuffle(cardStack: MutableList<String>) {
        cardStack.shuffle()
        println("Card deck is shuffled.")
    }
}