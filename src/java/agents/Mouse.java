import jason.asSyntax.*;

import jason.environment.Environment;
import jason.environment.grid.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mouse extends AgentBase {
    public Mouse(EnvironmentModel model, int agentId, List<Location> holesLocation) {
        super(model);

        AgentId = agentId;
        AgentName = "mouse_" + agentId;

        int position = Random.nextInt(holesLocation.size());
        Location holeLocation = holesLocation.get(position);

        SetInitialAgentPosition(holeLocation.x, holeLocation.y);
       	AddMovementPerception(GetCurrentLocation());
    }

    public void Move() {
        MoveRandomic();
    }
}