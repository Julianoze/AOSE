+position(L, X, Y) : true
	<- 	!move (X, Y).

+!move (X, Y) : X < 23
	<- 	.wait (500);
		moveDeliveryMan.

+!move (X, Y) : X == 23 & Y < 23
	<- 	.wait (500);
		moveDeliveryMan.

+!move (X, Y) : true.
