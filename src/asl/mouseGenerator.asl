!start.

+!start : true <- for ( .range(I, 11, 19) ) {
					.concat("mouse_", I, X);
					.create_agent(X, "mouse.asl");
				}.

