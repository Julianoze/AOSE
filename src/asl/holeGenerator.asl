!start.

+!start : true <- for ( .range(I, 1, 4) ) {
					.concat("hole_",I, X);
					.create_agent(X, "hole.asl");
				}.
