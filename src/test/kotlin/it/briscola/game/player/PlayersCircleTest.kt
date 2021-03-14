package it.briscola.game.player

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class PlayersCircleTest {

    @Test
    fun `check that the players list is created properly`() {
        val players = listOf(DumbPlayer("P1"), DumbPlayer("P2"), DumbPlayer("P3"))

        var head = PlayersCircle(players).generateCircle()
        assertEquals("P1", head.player.name)
        assertNotNull(head.next)
        head = head.next!!
        assertEquals("P2", head.player.name)
        assertNotNull(head.next)
        head = head.next!!
        assertEquals("P3", head.player.name)
        assertNotNull(head.next)
        head = head.next!!
        assertEquals("P1", head.player.name)
    }

    @Test
    fun `test iterator`() {
        val players = listOf(DumbPlayer("P1"), DumbPlayer("P2"), DumbPlayer("P3"))
        val circle = PlayersCircle(players)
        circle.generateCircle()

        var counter = 0
        circle.forEach { counter++ }

        assertEquals(3, counter)

    }
}