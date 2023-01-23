+position(L, X, Y) : true
	<- 	!move (X, Y).

+!move (X, Y) : X >= 0 & Y < 24
	<- 	.wait (300);
		moveDog.

+huntingCat(X, L) : true
	<- .wait(300);
	    hunting.

+catchedCat(X) : true
	<-  .concat("cat_", X, I);
		.kill_agent(I);
		decreaseCat;
		moveDog.