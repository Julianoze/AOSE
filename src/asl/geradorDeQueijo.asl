!start.

+!start : true <- for ( .range(I, 1, 12) ) { 
					.concat("queijo_", I, X);
					.create_agent(X, "gato.asl"); 
				}.
