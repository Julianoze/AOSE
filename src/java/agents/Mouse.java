import jason.asSyntax.*;

import jason.environment.Environment;
import jason.environment.grid.Location;

public class Mouse extends AgentBase {
    public Mouse(EnvironmentModel model, int agentId) {
        super(model);

        AgentId = agentId;
        AgentName = "mouse_" + agentId;

        SetRandomInitialAgentPosition();
       	AddMovementPerception(GetCurrentLocation());
    }

    public void Move() {
        MoveRandomic();
    }
}