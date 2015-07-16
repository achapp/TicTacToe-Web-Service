package com.achappell.resources;

import com.achappell.core.ExceptionResponse;
import com.achappell.core.IllegalMoveException;
import com.achappell.core.TicTacToeGame;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

@Path("/tictactoe")
@Produces(MediaType.APPLICATION_JSON)
public class TicTacToeResource {
    private TicTacToeGame game;

    public TicTacToeResource() {
        game = new TicTacToeGame();
    }

    @GET
    @Timed
    public Response getGame()
    {
        return Response.ok(game, MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Timed
    public Response resetGame()
    {
        game = new TicTacToeGame();
        return Response.noContent().build();
    }

    @POST
    @Path("/board")
    @Timed
    public Response move(@FormParam("player") char p, @FormParam("row") int r, @FormParam("col") int c)
    {
        try
        {
            game.play(p, r, c);
            return Response.ok(game, MediaType.APPLICATION_JSON).build();
        }
        catch (IllegalMoveException e)
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ExceptionResponse(Response.Status.BAD_REQUEST, e.getMessage())).build();
        }
    }

}