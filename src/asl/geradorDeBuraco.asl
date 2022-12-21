!start.

+!start : true <- for ( .range(I, 1, 4) ) { 
					.concat("buraco_",I, X);
					.create_agent(X, "buraco.asl"); 
				}.
