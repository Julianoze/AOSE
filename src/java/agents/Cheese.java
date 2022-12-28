import jason.asSyntax.*;

import jason.environment.Environment;
import jason.environment.grid.Location;

public class Cheese extends AgentBase {
    public Cheese(EnvironmentModel model, int agentId) {
        super(model);

        AgentId = agentId;
        AgentName = "cheese_" + agentId;

        SetRandomInitialAgentPosition();
    }

    public void Action(String agentName, Structure action) {
        if(!agentName.equals(AgentName))
            return;

        Location currentLocation = GetCurrentLocation();
        if(Model.isFree(currentLocation))
        {
            SetAgentPosition(currentLocation);
        }

        System.out.println(action);
    }
}