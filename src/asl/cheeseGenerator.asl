!start.

+!start : true <- for ( .range(I, 20, 31) ) {
					.concat("cheese_", I, X);
					.create_agent(X, "cheese.asl");
				}.
