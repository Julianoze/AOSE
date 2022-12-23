import jason.asSyntax.*;

import jason.environment.Environment;
import jason.environment.grid.Location;

public class HouseWife extends AgentBase {
    private boolean foundMouse;

    public HouseWife(EnvironmentModel model) {
        super(model);

        AgentId = 0;
        SetInitialAgentPosition(0, 0);
        AddMovePercept(GetCurrentLocation());
    }

    public void Move() {
        Location currentLocation = GetCurrentLocation();

        SearchMouse(currentLocation);

        if(foundMouse)
            return;

        currentLocation.x++;

        if (currentLocation.x == 10) {
        	currentLocation.x = 0;
        	currentLocation.y++;
        }
        if (currentLocation.y == 10) {
            return;
        }
        SetAgentPosition(currentLocation);
        AddMovePercept(currentLocation);
    }

    public void SearchMouse(Location currentLocation) {
        int agentColumn = currentLocation.x;
    	for (int column = agentColumn; column <= (agentColumn + 3); column++) {
        	// if ((column == ratoLoc.x) && (currentLocation.y == ratoLoc.y)) {
        	// 	    ratoAchado = 1;
        	//     Literal ratoPercebido = Literal.parseLiteral("ratoPercebido(" + ratoLoc.x +"," + ratoLoc.y + ")");
        	//     Environment.addPercept(ratoPercebido);
        	// }
       	}
    }

    private void AddMovePercept(Location location) {
        AddPercept(Literal.parseLiteral("position(houseWife," + location.x + "," + location.y + ")"));
    }
}