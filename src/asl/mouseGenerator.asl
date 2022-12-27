!start.

+!start : true <- for ( .range(I, 1, 9) ) {
					.concat("mouse_", I, X);
					.create_agent(mouse, "mouse.asl");
				}.

