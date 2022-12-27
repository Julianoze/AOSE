+position(L, X, Y) : true
	<- 	!move (X, Y).

+!move (X, Y) : X < 9
	<- 	.wait (500);
		moveDeliveryMan.

+!move (X, Y) : X == 9 & Y < 9
	<- 	.wait (500);
		moveDeliveryMan.

+!move (X, Y) : true.
