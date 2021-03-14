package it.briscola.game.model

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `test card of same suit and higher value is bigger`() {
        val card1 = Card(2,0, Suit.COPPE)
        val card2 = Card(5,0, Suit.COPPE)
        assertTrue(card2 > card1)
    }

    @Test
    fun `test card of same suit and lower value is smaller`() {
        val card1 = Card(5,0, Suit.COPPE)
        val card2 = Card(2,0, Suit.COPPE)
        assertTrue(card2 < card1)
    }

    @Test
    fun `test ace of same suit is bigger`() {
        val card1 = Card(5,0, Suit.COPPE)
        val card2 = Card(1,11, Suit.COPPE)
        assertTrue(card2 > card1)
    }

    @Test
    fun `test card of different suit and higher value is smaller`() {
        val card1 = Card(2,0, Suit.COPPE)
        val card2 = Card(5,0, Suit.SPADE)
        assertTrue(card2 < card1)
    }

    @Test
    fun `test card of different suit and equal value is smaller`() {
        val card1 = Card(2,0, Suit.COPPE)
        val card2 = Card(2,0, Suit.SPADE)
        assertTrue(card2 < card1)
    }

    @Test
    fun `test card of different suit and lower value is smaller`() {
        val card1 = Card(5,0, Suit.COPPE)
        val card2 = Card(2,0, Suit.SPADE)
        assertTrue(card2 < card1)
    }
}