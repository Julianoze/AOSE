!start.

+!start : true <- for ( .range(I, 4, 7) ) {
					.concat("hole_",I, X);
					.create_agent(X, "hole.asl");
				}.
