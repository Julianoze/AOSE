!start.

+!start : true <- for ( .range(I, 1, 3) ) {
					.concat("cat_", I, X);
					.create_agent(X, "cat.asl");
				}.
