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
        MoveLinear();
    }

    private void SearchMouse(Location currentLocation) {
        boolean foundMouse = false;
        int mouseId = 0;

		for(int i = 11; i <= 19; i++)
        {
            Location location = Model.getAgPos(i);
            int x = location.x - currentLocation.x;
            int y = location.y - currentLocation.y;


            if((x >= -3 && x <= 3) && (y >= -3 && y <= 3))
            {
                mouseId = i;
                foundMouse = true;
            }
        }

        if(foundMouse)
        {
            Model.Environment.addPercept(Literal.parseLiteral("foundMouse(" + mouseId + ")"));
        }

    }
}