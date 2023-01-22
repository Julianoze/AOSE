
+position(L, X, Y) : true
	<- 	!move (X, Y).

+run(L, X, Y) : true
	<-	.wait(1000);
		!stillRunning(X, Y).

+!stillRunning(X, Y) : true
	<-	runaway.

+hide(L) : true
	<- 	.wait(1000);
		respawn.

+eat(L, X) : true
	<- 	.wait(1000);
		.concat("cheese_", X, I);
		.kill_agent(I);
		decreaseCheese;
		restart.

+!move (X, Y) : X >= 0 & Y < 24
	<- 	.wait (1000);
		moveMouse.
