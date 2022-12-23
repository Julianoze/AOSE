!start.

+!start : true <- for ( .range(I, 1, 12) ) { 
					.concat("cheese_", I, X);
					.create_agent(X, "cheese.asl"); 
				}.
