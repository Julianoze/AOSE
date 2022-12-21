!start.

+!start : true <- for ( .range(I, 1, 3) ) { 
					.concat("gato_", I, X);
					.create_agent(X, "gato.asl"); 
				}.
