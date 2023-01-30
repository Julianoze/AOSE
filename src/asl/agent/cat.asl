+position(L, X, Y) : true
	<- 	!move (X, Y).

+run(L, X, Y) : true
	<-	.wait(1000);
		!stillRunning(X, Y).

+!stillRunning(X, Y) : true
	<-	runaway.

+!move (X, Y) : X >= 0 & Y < 24
	<- 	.wait (700);
		moveCat.

+foundMouse(X): true
	<- huntMouse(X).

+!move (X, Y) : true.

+huntingMouse(X, L) : true
				<- .wait(1000);
				    hunting.

+catchedMouse(X) : true
				<-  .concat("mouse_", X, I);
				    .print ("Nham! Nham! Nham!");
					.kill_agent(I);
					decreaseMouse;
					moveCat.