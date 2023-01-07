import jason.asSyntax.*;

import jason.environment.Environment;
import jason.environment.grid.Location;

import java.util.List;

public class Cat extends AgentBase {
    private List<Location> HolesLocation;
    private int _mouseId;

    public Cat(EnvironmentModel model,
               int agentId,
               List<Location> holesLocation) {
        super(model);

        AgentId = agentId;
        AgentName = "cat_" + agentId;

        HolesLocation = holesLocation;

        SetRandomInitialAgentPosition();
       	AddMovementPerception(GetCurrentLocation());
    }

    public void Action(String agentName, Structure action) {
        if(!agentName.equals(AgentName))
            return;

        // TODO if mouse enter in a hole add movement perception
        if(action.equals(Literal.parseLiteral("hunting")))
        {
            Location currentLocation = GetCurrentLocation();
            RemovePerception("huntingMouse(" + currentLocation.x + "," + currentLocation.y + ")");

            Location mouseLocation = Model.getAgPos(_mouseId);

            if(isMouseHoverHole(mouseLocation))
            {
                Model.Environment.clearPercepts(agentName);
                AddMovementPerception(currentLocation);
                return;
            }

            if(currentLocation.x == mouseLocation.x && currentLocation.y == mouseLocation.y)
            {
                Model.Environment.clearPercepts(agentName);
                AddAgentPerception("catchedMouse(" + _mouseId + ")");
                return;
            }

            if(currentLocation.x > mouseLocation.x)
            {
                currentLocation.x--;
            } else if (currentLocation.x < mouseLocation.x) {
                currentLocation.x++;
            }

            if(currentLocation.y > mouseLocation.y)
            {
                currentLocation.y--;
            } else if (currentLocation.y < mouseLocation.y) {
                currentLocation.y++;
            }

            AddAgentPerception("huntingMouse(" + currentLocation.x + "," + currentLocation.y + ")");
            SetAgentPosition(currentLocation);
            return;
        }


        Move();
    }

    public void Move() {
        Location currentLocation = GetCurrentLocation();
        if(SearchMouse(currentLocation))
            return;

        MoveRandomic();
    }

    private boolean SearchMouse(Location currentLocation) {
        boolean foundMouse = false;
        Location mouseLocation = currentLocation;

		for(int i = 11; i <= 19; i++)
        {
            Location location = Model.getAgPos(i);
            int x = location.x - currentLocation.x;
            int y = location.y - currentLocation.y;


            if((x >= -3 && x <= 3) && (y >= -3 && y <= 3))
            {
                _mouseId = i;
                mouseLocation = location;
                foundMouse = true;
            }
        }

        if(!foundMouse)
            return foundMouse;

        boolean mouseOverHole = isMouseHoverHole(mouseLocation);
        if(!mouseOverHole)
        {
            RemoveMovementPerception(currentLocation);
            AddAgentPerception("huntingMouse(" + currentLocation.x + "," + currentLocation.y + ")");
        }

        return !mouseOverHole;
    }

    private boolean isMouseHoverHole(Location mouseLocation)
    {
        boolean mouseOverHole = false;

        for(Location location : HolesLocation)
        {
            if(location.x == mouseLocation.x && location.y == mouseLocation.y)
                mouseOverHole = true;
        }

        return mouseOverHole;
    }
}