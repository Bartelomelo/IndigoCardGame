package indigo

class Game {
    private val deck = Deck()
    private val player = Player()
    private val gameTable = Table()
    private val computer = Computer()
    lateinit var lastWinner: String
    private fun showScore() {
        println(
            "Score: Player ${player.score} - Computer ${computer.score}\n" +
                    "Cards: Player ${player.winCards} - Computer ${computer.winCards}"
        )
    }

    private fun gameDraw(isPlayer: String) {
        if (player.winCards == computer.winCards) {
            if (isPlayer == "yes") {
                player.score += 3
            } else {
                computer.score += 3
            }

        }
    }

    private fun pointsForMostCards() {
        if (player.winCards != computer.winCards) {
            if (player.winCards > computer.winCards) {
                player.score += 3
            } else {
                computer.score += 3
            }
        }

    }

    private fun additionalCardsAndPoints(lastWinner: String) {
        if (lastWinner == "player") {
            player.updateWinCards(gameTable.getCardsCount())
            player.updateScore(gameTable.score())
        } else {
            computer.updateWinCards(gameTable.getCardsCount())
            computer.updateScore(gameTable.score())
        }
    }

    private fun playerFirst(): String {
        var whoPlay: String
        do {
            println("Play first?")
            whoPlay = readln()
        } while (whoPlay != "yes" && whoPlay != "no")
        return whoPlay
    }

    fun gameStart() {
        println("Indigo Card Game")
        when (val whoFirst = playerFirst()) {
            "yes" -> {
                deck.buildDeck()
                gameTable.fillTable(deck.cardStack)
                while (deck.cardStack.size > 0 || player.getCardsCount() > 0 || computer.getCardsCount() > 0) {
                    gameTable.cardsOnTable()
                    player.addCards(deck.cardStack)
                    computer.addCards(deck.cardStack)
                    gameTable.getCardFromPlayer(player.putCard())
                    if (gameTable.isCardSame() == true) {
                        println("Player wins cards")
                        player.updateScore(gameTable.score())
                        player.updateWinCards(gameTable.winCards())
                        showScore()
                        gameTable.clearTable()
                        lastWinner = "player"
                    }
                    gameTable.cardsOnTable()
                    computer.computerAI(gameTable.getLastCard(), gameTable.getCardsCount())
                        ?.let { gameTable.getCardFromPlayer(it) }
                    if (gameTable.isCardSame() == true) {
                        println("Computer wins cards")
                        computer.updateScore(gameTable.score())
                        computer.updateWinCards(gameTable.winCards())
                        showScore()
                        gameTable.clearTable()
                        lastWinner = "computer"
                    }
                }
                gameTable.cardsOnTable()
                gameDraw(whoFirst)
                additionalCardsAndPoints(lastWinner)
                pointsForMostCards()
                showScore()
                println("Game Over")

            }
            "no" -> {
                deck.buildDeck()
                gameTable.fillTable(deck.cardStack)
                while (deck.cardStack.size > 0 || player.getCardsCount() > 0 || computer.getCardsCount() > 0) {
                    gameTable.cardsOnTable()
                    player.addCards(deck.cardStack)
                    computer.addCards(deck.cardStack)
                    computer.computerAI(gameTable.getLastCard(), gameTable.getCardsCount())
                        ?.let { gameTable.getCardFromPlayer(it) }
                    if (gameTable.isCardSame() == true) {
                        println("Computer wins cards")
                        computer.updateScore(gameTable.score())
                        computer.updateWinCards(gameTable.winCards())
                        showScore()
                        gameTable.clearTable()
                        lastWinner = "computer"
                    }
                    gameTable.cardsOnTable()
                    gameTable.getCardFromPlayer(player.putCard())
                    if (gameTable.isCardSame() == true) {
                        println("Player wins cards")
                        player.updateScore(gameTable.score())
                        player.updateWinCards(gameTable.winCards())
                        showScore()
                        gameTable.clearTable()
                        lastWinner = "computer"
                    }
                }
                gameTable.cardsOnTable()
                gameDraw(whoFirst)
                additionalCardsAndPoints(lastWinner)
                pointsForMostCards()
                showScore()
                println("Game Over")

            }
        }
    }
}