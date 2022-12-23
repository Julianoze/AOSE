+positionCat(L, X, Y) : true
	<- 	!mover (X, Y).

+!mover (X, Y) : X < 9
	<- 	.wait (300);
		moveCat.

+!mover (X, Y) : X == 9 & Y < 9
	<- 	.wait (300);
		moveCat.

+!mover (X, Y) : true.

+aindaNaoPegou (X,Y) : true
				<- !pegaRato.

+ratoApanhado : true
				<- .print ("Nham! Nham! Nham!");
				   .kill_agent (rato1).

+!pegaRato [source (donaCasa)] : true
				<- .wait(100);
				   .print ("Ca�ando o rato");
				    proximaCasaGato.

+!pegaRato [source (self)] : true
				<- .wait(100);
				   .print ("Ca�ando o rato");
				    proximaCasaGato.
