+position(L, X, Y) : true
	<- 	!move (X, Y).

+!move (X, Y) : X < 9
	<- 	.wait (300);
		moveMouse.

+!move (X, Y) : X == 9 & Y < 9
	<- 	.wait (300);
		moveMouse.
