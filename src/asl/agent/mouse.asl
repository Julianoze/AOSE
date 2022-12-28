
+position(L, X, Y) : true
	<- 	!move (X, Y).

+hide(L) : true
	<- 	.wait(1000);
		respawn.

+eat(L, X) : true
	<- 	.wait(1000);
		.concat("cheese_", X, I);
		.kill_agent(I);
		restart.

+!move (X, Y) : X >= 0 & Y < 10
	<- 	.wait (900);
		moveMouse.
