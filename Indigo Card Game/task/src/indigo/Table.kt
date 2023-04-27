package indigo

class Table {
    val cardsOnTable = mutableListOf<Card>()


    fun fillTable(deck: MutableList<Card>) {
        if (cardsOnTable.isEmpty()) {
            for (i in 0..3) {
                cardsOnTable.add(deck[i])
            }
            for (i in 0..3) {
                deck.removeAt(0)
            }
            print("Initial cards on the table: ")
            for (i in cardsOnTable) {
                print("$i ")
            }
            println()
            println()
        }

    }

    fun cardsOnTable(): Card? {
        return if (cardsOnTable.isEmpty()) {
            println("No cards on the table")
            null
        } else {
            println("${cardsOnTable.size} cards on the table, and the top card is ${cardsOnTable[cardsOnTable.lastIndex]}")
            cardsOnTable[cardsOnTable.lastIndex]
        }

    }

    fun getCardFromPlayer(card: Card): Card {
        cardsOnTable.add(card)
        return card
    }

    fun score(): Int {
        var points = 0
        for (card in cardsOnTable) {
            if (card.rank == "A" || card.rank == "K" || card.rank == "Q" || card.rank == "J" || card.rank == "10") {
                points++
            }
        }
        return points
    }

    fun winCards(): Int {
        return cardsOnTable.size
    }

    private fun isCardMatch(playerCard: Card, otherCard: Card): Boolean {
        return playerCard.rank == otherCard.rank || playerCard.suit == otherCard.suit
    }

    fun isCardSame(): Boolean? {
        return if (cardsOnTable.size > 1) {
            isCardMatch(cardsOnTable[cardsOnTable.lastIndex], cardsOnTable[cardsOnTable.lastIndex - 1])
        } else {
            null
        }

    }

    fun getLastCard(): Card? {
        return if (getCardsCount() != 0) {
            cardsOnTable[cardsOnTable.lastIndex]
        } else {
            null
        }

    }

    fun getCardsCount(): Int {
        return cardsOnTable.size
    }

    fun clearTable() {
        cardsOnTable.clear()
    }

    fun isTableNotEmpty(): Boolean {
        return cardsOnTable.isNotEmpty()
    }

}