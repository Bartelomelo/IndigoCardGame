package indigo

import kotlin.random.Random
import kotlin.random.nextInt

class Computer : Player() {
    private val cards = mutableListOf<Card>()

    override fun addCards(deck: MutableList<Card>) {
        if (cards.isEmpty() && deck.isNotEmpty()) {
            for (i in 0..5) {
                cards.add(deck[i])
            }
            for (i in 0..5) {
                deck.removeAt(0)
            }
        }
    }

    private fun printChoice(card: Card) {
        for (cardInHand in cards) {
            print("$cardInHand ")
        }
        println()
        println("Computer plays $card")
        println()
    }

    fun computerAI(topCard: Card?, cardsOnTable: Int): Card? {
        val allSuits = mutableListOf<String>()
        val allRanks = mutableListOf<String>()
        val suitsCount = mutableMapOf<String, Int>()
        val ranksCount = mutableMapOf<String, Int>()
        val candidates = mutableListOf<Card>()
        val playingCard: Card
        cards.forEach { allSuits.add(it.suit); allRanks.add(it.rank) }
        suitsCount["♠"] = allSuits.count { it == "♠" }
        suitsCount["♥"] = allSuits.count { it == "♥" }
        suitsCount["♦"] = allSuits.count { it == "♦" }
        suitsCount["♣"] = allSuits.count { it == "♣" }
        ranksCount["2"] = allRanks.count { it == "2" }
        ranksCount["3"] = allRanks.count { it == "3" }
        ranksCount["4"] = allRanks.count { it == "4" }
        ranksCount["5"] = allRanks.count { it == "5" }
        ranksCount["6"] = allRanks.count { it == "6" }
        ranksCount["7"] = allRanks.count { it == "7" }
        ranksCount["8"] = allRanks.count { it == "8" }
        ranksCount["9"] = allRanks.count { it == "9" }
        ranksCount["10"] = allRanks.count { it == "10" }
        ranksCount["J"] = allRanks.count { it == "J" }
        ranksCount["Q"] = allRanks.count { it == "Q" }
        ranksCount["K"] = allRanks.count { it == "K" }
        ranksCount["A"] = allRanks.count { it == "A" }
        var candidateCardsCounter = 0
        for (card in cards) {
            if (topCard != null) {
                if (card.rank == topCard.rank || card.suit == topCard.suit) {
                    candidateCardsCounter++
                }
            }
        }

        if (cards.size == 1) { //przypadek nr 1
            candidates.addAll(cards)
            playingCard = candidates[0]
            printChoice(playingCard)
            cards.remove(playingCard)
            return playingCard
        }

        if (cardsOnTable == 0 || candidateCardsCounter == 0) { //przypadek nr 3
            var suitNotFound = true
            var ranksNotFound = true
            for (suits in suitsCount) {
                if (suits.value > 1) {
                    candidates.addAll(cards.filter { it.suit == suits.key })
                    suitNotFound = false
                    ranksNotFound = false
                }
            }
            if (suitNotFound) {
                for (ranks in ranksCount) {
                    if (ranks.value > 1) {
                        candidates.addAll((cards.filter { it.rank == ranks.key }))
                        ranksNotFound = false
                    }
                }
            }
            if (ranksNotFound) {
                candidates.addAll(cards)
            }
            playingCard = candidates[Random.nextInt(0..candidates.lastIndex)]
            printChoice(playingCard)
            cards.remove(playingCard)
            return playingCard
        }

        if (candidateCardsCounter == 1) { //przypadek 2
            if (topCard != null) {
                val candidateSuit = cards.filter { it.suit == topCard.suit }
                val candidateRank = cards.filter { it.rank == topCard.rank }
                if (candidateSuit.isNotEmpty()) {
                    candidates.add(candidateSuit[0])
                } else if (candidateRank.isNotEmpty()) {
                    candidates.add(candidateRank[0])
                }
                playingCard = candidates[0]
                printChoice(playingCard)
                cards.remove(playingCard)
                return playingCard
            }
        } else if (candidateCardsCounter > 1) { //przypadek 5
            if (topCard != null) {
                var suitNotFound = true
                val candidateSuit = cards.filter { it.suit == topCard.suit }
                candidates.addAll(candidateSuit)
                if (candidates.isNotEmpty()) {
                    suitNotFound = false
                }
                if (suitNotFound) {
                    candidates.addAll((cards.filter { it.rank == topCard.rank }))
                }
                playingCard = candidates[Random.nextInt(0..candidates.lastIndex)]
                printChoice(playingCard)
                cards.remove(playingCard)
                return playingCard
            }
        }
        return null
    }
}