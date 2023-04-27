package indigo

import kotlin.system.exitProcess

open class Player() {
    private val cardsInHand = mutableListOf<Card>()
    var score: Int = 0
    var winCards: Int = 0

    fun updateScore(points: Int) {
        score += points
    }

    fun updateWinCards(numberOfCards: Int) {
        winCards += numberOfCards
    }

    private fun isStringNumeric(input: String): Boolean {
        var isNumeric = true
        for (number in input) {
            if (number !in 48.toChar()..57.toChar()) {
                isNumeric = false
            }
        }
        return isNumeric
    }

    override fun toString(): String {
        val cards = ""
        for (card in cardsInHand) {
            println(card)
        }
        return cards
    }

    open fun addCards(deck: MutableList<Card>) {
        if (cardsInHand.isEmpty() && deck.isNotEmpty()) {
            for (i in 0..5) {
                cardsInHand.add(deck[i])
            }
            for (i in 0..5) {
                deck.removeAt(0)
            }
        }
    }


    open fun putCard(): Card {
        var finalChoice: String
        print("Cards in hand: ")
        for (i in cardsInHand.indices) {
            print("${i + 1})${cardsInHand[i]} ")
        }
        println()
        //println("Choose a card to play (1-${cardsInHand.lastIndex + 1}):")
        do {
            println("Choose a card to play (1-${cardsInHand.lastIndex + 1}):")
            val choice = readln()
            if (choice == "exit") {
                println("Game Over")
                exitProcess(1)
            }
            finalChoice = choice
        } while (!isStringNumeric(choice) || choice.toInt() !in 1..cardsInHand.lastIndex + 1)
        println()
        return cardsInHand.removeAt(finalChoice.toInt() - 1)
    }

    fun getCardsCount(): Int {
        return cardsInHand.size
    }

}