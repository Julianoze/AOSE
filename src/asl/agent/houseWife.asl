// Agent sample_agent in project amb5

/* Initial beliefs and rules */

/* Initial goals */

+position(L, X, Y) : true
	<- 	!move (X, Y).

+ratoPercebido(X, Y) : true
	<- 	.print("O rato est� na posi��o ", X, " e ", Y);
		.send (gato_1, achieve, pegaRato).

+!move (X, Y) : X < 23
	<- 	.wait (500);
		moveHouseWife.

+!move (X, Y) : X == 23 & Y < 23
	<- 	.wait (500);
		moveHouseWife.

+!move (X, Y) : true.
