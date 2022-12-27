+position(L, X, Y) : true
	<- 	!move (X, Y).

+!move (X, Y) : X >= 0 & Y < 10
	<- 	.wait (300);
		moveDog.
