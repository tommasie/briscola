package it.briscola.game.player

import it.briscola.game.controller.GameController
import it.briscola.game.model.Card

abstract class IPlayer(
    val name: String,
) {
    val cards = mutableListOf<Card>()
    var score = 0

    fun giveCard(card: Card) = cards.add(card)

    fun giveTurn(controller: GameController) = controller.play(this, chooseCard().also { cards.remove(it) })

    abstract  fun chooseCard(): Card

}