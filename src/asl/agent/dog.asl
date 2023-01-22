+position(L, X, Y) : true
	<- 	!move (X, Y).

+!move (X, Y) : X >= 0 & Y < 24
	<- 	.wait (300);
		moveDog.
