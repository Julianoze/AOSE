import jason.asSyntax.*;

import jason.environment.Environment;
import jason.environment.grid.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mouse extends AgentBase {
    private List<Location> HolesLocation;
    private List<Location> CheeseLocation;

    private int _cheeseAgentId;
    private boolean _foundCheese;
    private Literal CheesePerception;

    public Mouse(EnvironmentModel model,
                 int agentId,
                 List<Location> holesLocation,
                 List<Location> cheeseLocation)
    {
        super(model);

        AgentId = agentId;
        AgentName = "mouse_" + agentId;

        HolesLocation = holesLocation;
        CheeseLocation = cheeseLocation;
        RespawnFromHole();
       	AddMovementPerception(GetCurrentLocation());
    }

    public void Action(String agentName, Structure action) {

        if(!agentName.equals(AgentName))
            return;

        if(action.equals(Literal.parseLiteral("respawn")))
        {
            RespawnFromHole();
        }

        if(action.equals(Literal.parseLiteral("restart")))
        {
            _foundCheese = false;
            Model.Environment.removePercept(AgentName, CheesePerception);
        }

        Move();
    }

    public void Move() {
        RemovePerception("hide("+ AgentName+")");
        MoveRandomic();

        Location currentLocation = GetCurrentLocation();

        EnterInHole(currentLocation);
        EatCheese(currentLocation);
    }

    private void RespawnFromHole() {
        int position = Random.nextInt(HolesLocation.size());
        Location holeLocation = HolesLocation.get(position);
        SetInitialAgentPosition(holeLocation.x, holeLocation.y);
    }

    private void EnterInHole(Location currentLocation) {
        if(!IsOverHole(currentLocation.x, currentLocation.y))
            return;

        int agent = Model.getAgAtPos(currentLocation);

        RemoveMovementPerception(currentLocation);
        AddAgentPerception("hide("+ AgentName + ")");

        Model.setAgPos(agent, currentLocation);
    }

    private void EatCheese(Location currentLocation) {
        if(!_foundCheese)
            return;

        RemoveMovementPerception(currentLocation);
        CheesePerception = Literal.parseLiteral("eat("+ AgentName + "," + _cheeseAgentId + ")");
        Model.Environment.addPercept(AgentName, CheesePerception);
    }

    @Override
    protected boolean AllowedPositions(int x, int y)
    {
        if(IsOverHole(x, y))
            return true;

        if(IsOverCheese(x, y))
            return true;

        return false;
    }

    protected boolean IsOverHole(int x, int y)
    {
        boolean isAllowedPosition = false;

        for(Location location : HolesLocation)
        {
            if(location.x == x && location.y == y)
                isAllowedPosition = true;
        }

        return isAllowedPosition;
    }

    protected boolean IsOverCheese(int x, int y)
    {
        for(Location location : CheeseLocation)
        {
            if(location.x == x && location.y == y)
            {
                _foundCheese = true;
                _cheeseAgentId = Model.getAgAtPos(x, y);
            }
        }

        return _foundCheese;
    }
}