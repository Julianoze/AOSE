import jason.asSyntax.*;

import jason.environment.Environment;
import jason.environment.grid.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mouse extends AgentBase {
    private List<Location> HolesLocation;

    public Mouse(EnvironmentModel model, int agentId, List<Location> holesLocation) {
        super(model);

        AgentId = agentId;
        AgentName = "mouse_" + agentId;

        HolesLocation = holesLocation;

        int position = Random.nextInt(HolesLocation.size());
        Location holeLocation = HolesLocation.get(position);

        SetInitialAgentPosition(holeLocation.x, holeLocation.y);
       	AddMovementPerception(GetCurrentLocation());
    }

    public void Action(String agentName, Structure action) {
        if(!agentName.equals(AgentName))
            return;

        Move();
    }


    public void Move() {
        MoveRandomic();
    }

    @Override
    protected boolean AllowedPositions(int x, int y)
    {
        boolean isAllowedPosition = false;

        for(Location location : HolesLocation)
        {
            if(location.x == x && location.y == y)
            {
                isAllowedPosition = true;
                return true;
            }
        }

        return isAllowedPosition;
    }
}