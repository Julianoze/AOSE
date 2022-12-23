// Agent sample_agent in project amb5

/* Initial beliefs and rules */

/* Initial goals */

+position(L, X, Y) : true
	<- 	!move (X, Y).

+ratoPercebido(X, Y) : true
	<- 	.print("O rato est� na posi��o ", X, " e ", Y);
		.send (gato_1, achieve, pegaRato).

+!move (X, Y) : X < 9
	<- 	.wait (300);
		moveHouseWife.

+!move (X, Y) : X == 9 & Y < 9
	<- 	.wait (300);
		moveHouseWife.

+!move (X, Y) : true.
