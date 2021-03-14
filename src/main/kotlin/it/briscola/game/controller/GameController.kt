package it.briscola.game.controller

import it.briscola.game.model.Card
import it.briscola.game.model.Suit
import it.briscola.game.player.IPlayer
import it.briscola.game.player.PlayersCircle
import org.jboss.logging.Logger
import java.util.*
import kotlin.collections.HashSet
import kotlin.random.Random

class GameController {

    private val logger = Logger.getLogger(GameController::class.java)

    val players = mutableListOf<IPlayer>()
    val deck = Stack<Card>()
    val plate = HashSet<Card>()
    var rounds = 0
    lateinit var briscola: Suit
    lateinit var playersCircle: PlayersCircle
    var handWinner: IPlayer? = null
    var winningCard: Card? = null

    fun addPlayer(player: IPlayer) = players.add(player)

    fun initGame() {
        logger.info("Starting game with ${players.size} players")
        initDeck()
        rounds = deck.size / players.size
        initPlayerCircle()
        chooseStartingPlayer()
        giveCards()
    }

    fun playGame() {
        while(rounds-- > 0) {
            roundLoop()
        }
        endGameReport()
    }

    fun roundLoop() {
        if(deck.isNotEmpty()) {
            playersCircle.forEach { it.player.giveCard(deck.pop()) }
        }
        playersCircle.forEach {
            it.player.giveTurn(this)
        }
        val points = plate.fold(0) { counter, card -> counter + card.value }
        logger.info("${handWinner!!.name} has won the hand and gets $points")

        handWinner!!.score += points
        playersCircle.setHead(handWinner!!)
        handWinner = null
        winningCard = null

        plate.clear()
    }

    fun play(player: IPlayer, card: Card) {
        logger.debug("Player ${player.name} has played ${card.number} of ${card.suit}")
        if(plate.isEmpty() || card > winningCard!!) {
            handWinner = player
            winningCard = card
        }
        plate.add(card)
    }

    fun initDeck() {
        logger.info("Setting up deck...")
        Suit.values().forEach { suit ->
            IntRange(1,10).forEach { value ->
                deck.push(
                    Card(
                        value,
                        when(value) {
                            8 -> 2
                            9 -> 3
                            10 -> 4
                            3 -> 10
                            1 -> 11
                            else -> 0
                        },
                        suit)
                )
            }
        }
        Collections.shuffle(deck)
        logger.info("Deck set up")
    }

    fun initPlayerCircle() {
        playersCircle = PlayersCircle(players)
    }

    fun chooseStartingPlayer()  {
        playersCircle.setHead(players[Random.nextInt(players.size)])
    }

    fun giveCards() {
        playersCircle.forEach { player ->
            repeat(3) { player.player.giveCard(deck.pop()) }
        }
    }

    fun endGameReport() {
        val winner = players.find { it.score > 120 / players.size }
        logger.info("The winner of the game is ${winner?.name} with ${winner?.score} points")
    }

}