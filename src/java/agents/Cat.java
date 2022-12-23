import jason.asSyntax.*;

import jason.environment.Environment;
import jason.environment.grid.Location;

public class Cat extends AgentBase {
    public Cat(EnvironmentModel model, int agentId) {
        super(model);

        AgentId = agentId;

        SetRandomInitialAgentPosition();
       	// AddMovePercept(GetCurrentLocation());
    }

    public void Move() {
        // gatoLoc = getAgPos(1);
        // ratoLoc = getAgPos(2);

        // if ((gatoLoc.x == ratoLoc.x) && (gatoLoc.y == ratoLoc.y)) {
        //     Literal ratoApanhado = Literal.parseLiteral("ratoApanhado");
        //     Environment.addPercept(ratoApanhado);
        // }
        // else {

        // 	if (ratoLoc.x > gatoLoc.x) {
        //    		gatoLoc.x++;
        // 	}

        // 	if (ratoLoc.x < gatoLoc.x) {
        //    		gatoLoc.x--;
        // 	}

        // 	if (ratoLoc.x == gatoLoc.x) {
        // 	   if (ratoLoc.y > gatoLoc.y) {
        // 		   gatoLoc.y++;
        // 	   }

        // 	   if (ratoLoc.y < gatoLoc.y) {
        // 		   gatoLoc.y--;
        // 	   }

        // 	}
        // }

        // setAgPos(1, gatoLoc);
        // Literal perseguicao = Literal.parseLiteral("aindaNaoPegou (" + gatoLoc.x + ", " + gatoLoc.y + ")");
       	// Environment.addPercept(perseguicao);

        // Location currentLocation = GetCurrentLocation();

        // currentLocation.x++;

        // if (currentLocation.x == 10) {
        // 	currentLocation.x = 0;
        // 	currentLocation.y++;
        // }
        // if (currentLocation.y == 10) {
        //     return;
        // }

        // SetAgentPosition(currentLocation);
       	// AddMovePercept(currentLocation);
    }

    private void AddMovePercept(Location location) {
        AddPercept(Literal.parseLiteral("positionCat(cat_1," + location.x + "," + location.y + ")"));
    }
}