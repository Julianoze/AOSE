
+position(L, X, Y) : true
	<- 	!move (X, Y).

+hide(L) : true
	<- .wait(1000);
		respawn.

+!move (X, Y) : X >= 0 & Y < 10
	<- 	.wait (300);
		moveMouse.
