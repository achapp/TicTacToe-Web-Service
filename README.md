------------------------
TicTacToe API
------------------------

GET		/tictactoe
------------------
Parameters: None

Function: Provides data about the game. Data includes the board, the winner of the game, and 
the current player. Winner can be either None, Player X, Player O, or Tie.

Returns: JSON serialized version of the game

Example:
curl http://localhost:8080/tictactoe

{"board":["---","---","---"],"winner":"None","turn":"Player X"}



DELETE 	/tictactoe
-------------------
Parameters: None

Function: Resets the TicTacToe game.

Returns: None

Example:
curl -X DELETE http://localhost:8080/tictactoe



POST 	/tictactoe/board
------------------------
Parameters:
player (char) - the player to make the move for (either X or O case-insensitive)
row (int) - the row location of the move. Board is zero-indexed
col (int) - the column location of the move. Board is zero-indexed

Function: Plays the move at the row, column location for the player is possible.

Returns: On success, returns JSON serialized version of the game
		On failure, returns 400 Bad Request with reason for failure. Reasons include:
			- The game is already over and no more moves can be made
			- Not the given player's turn
			- Given coordinates don't exist on the board
			- A move has previously been made at the given coordinates

Example:
curl --data "player=x&row=1&col=1" ""http://localhost:8080/tictactoe/board

{"board":["---","-X-","---"],"winner":"None","turn":"Player O"}



