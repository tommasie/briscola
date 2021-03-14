package it.briscola

import it.briscola.game.controller.GameController
import it.briscola.game.player.DumbPlayer
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/game")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class Application {

    @GET
    fun all() {
        val p1 = DumbPlayer("P1")
        val p2 = DumbPlayer("P2")

        val controller = GameController()
        controller.addPlayer(p1)
        controller.addPlayer(p2)

        controller.initGame()
        controller.playGame()
    }


}