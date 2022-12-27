import jason.asSyntax.*;

import jason.environment.Environment;
import jason.environment.grid.Location;

public class Hole extends AgentBase {
    public Hole(EnvironmentModel model, int agentId) {
        super(model);

        AgentId = agentId;
        AgentName = "hole_" + agentId;

        SetRandomInitialAgentPosition();
    }

    public void Action(String agentName, Structure action) {
        Location currentLocation = GetCurrentLocation();
        if(Model.isFree(currentLocation))
        {
            SetAgentPosition(currentLocation);
        }
    }
}