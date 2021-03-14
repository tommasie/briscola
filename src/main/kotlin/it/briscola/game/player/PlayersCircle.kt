package it.briscola.game.player

class PlayersCircle(val players: List<IPlayer>) : Iterable<PlayersCircle.PlayerNode>{

    private lateinit var head: PlayerNode

    init {
        generateCircle()
    }

    data class PlayerNode(
        val player: IPlayer,
        var next: PlayerNode? = null,
    )

    fun generateCircle() : PlayerNode {
        val head = PlayerNode(players[0])
        var iter = head
        for(i in 1 until players.size) {
            val player = PlayerNode(players[i])
            iter.next = player
            iter = player
        }
        iter.next = head
        this.head = head
        return head
    }

    fun setHead(player: IPlayer) {
        while(player != head.player) head = head.next!!
    }

    class PlayerNodeIterator(private val circle: PlayersCircle) : Iterator<PlayerNode> {

        var curr: PlayerNode? = null

        override fun hasNext(): Boolean = (curr == null) || (curr!!.next != circle.head)

        override fun next(): PlayerNode {
            if(curr == null)
                curr = circle.head
            else curr = curr!!.next!!
            return curr!!
        }

    }

    override fun iterator() = PlayerNodeIterator(this)
}