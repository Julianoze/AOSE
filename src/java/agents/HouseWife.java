import jason.asSyntax.*;

import jason.environment.Environment;
import jason.environment.grid.Location;

public class HouseWife extends AgentBase {
    private boolean foundMouse;

    public HouseWife(EnvironmentModel model) {
        super(model);

        AgentId = 0;
        AgentName = "houseWife";

        SetInitialAgentPosition(0, 0);
        AddMovementPerception(GetCurrentLocation());
    }

    public void Action(String agentName, Structure action) {
        if(!agentName.equals(AgentName))
            return;

        Move();
    }

    public void Move() {
        Location currentLocation = GetCurrentLocation();

        SearchMouse(currentLocation);

        if(foundMouse)
            return;

        MoveLinear();
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
}