!start.

+!start : true <- for ( .range(I, 8, 10) ) {
					.concat("cat_", I, X);
					.create_agent(X, "cat.asl");
				}.
