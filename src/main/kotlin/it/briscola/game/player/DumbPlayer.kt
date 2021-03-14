package it.briscola.game.player

import it.briscola.game.model.Card
import kotlin.random.Random

class DumbPlayer(name: String) : IPlayer(name) {

    override fun chooseCard(): Card = cards[Random.nextInt(cards.size)]


}