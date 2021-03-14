package it.briscola.game.model

data class Card(
    val number: Int,
    val value: Int,
    val suit: Suit,
) : Comparable<Card> {
    override fun compareTo(reigning: Card): Int {
        if(suit != reigning.suit) return -1
        if(value > reigning.value) return 1
        if(value == reigning.value && number > reigning.number) return 1
        return -1
    }
}