package it.briscola.game.controller

import it.briscola.game.player.DumbPlayer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GameControllerTest {

    @Test
    fun `test deck building`() {
        val controller = GameController()
        assertEquals(0, controller.deck.size)

        controller.initDeck()
        assertEquals(40, controller.deck.size)
    }

    @Test
    fun `test all cards are different`() {
        val controller = GameController()
        controller.initDeck()
        assertEquals(40, HashSet(controller.deck).size)
    }

    @Test
    fun `test all players get 3 cards`() {
        val controller = GameController()
        val p1 = DumbPlayer("P1")
        val p2 = DumbPlayer("P2")
        controller.addPlayer(p1)
        controller.addPlayer(p2)
        controller.initPlayerCircle()

        controller.initDeck()
        controller.giveCards()

        assertEquals(34, controller.deck.size)
        assertEquals(3, p1.cards.size)
        assertEquals(3, p1.cards.size)
    }
}