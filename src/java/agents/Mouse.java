import jason.asSyntax.*;

import jason.environment.Environment;
import jason.environment.grid.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mouse extends AgentBase {
    private List<Location> HolesLocation;
    private List<Location> CheeseLocation;
    private List<Integer> CatsId;

    private int _cheeseAgentId;
    private Location _nearestHole;
    private boolean _foundCheese;
    private Literal CheesePerception;
    private Literal RunawayPerception;

    public Mouse(EnvironmentModel model,
                 int agentId,
                 List<Location> holesLocation,
                 List<Location> cheeseLocation,
                 List<Integer> catsId)
    {
        super(model);

        AgentId = agentId;
        AgentName = "mouse_" + agentId;

        HolesLocation = holesLocation;
        CheeseLocation = cheeseLocation;
        CatsId = catsId;

        RespawnFromHole();
       	AddMovementPerception(GetCurrentLocation());
    }

    public void Action(String agentName, Structure action) {

        if(!agentName.equals(AgentName))
            return;

        if(action.equals(Literal.parseLiteral("runaway")))
        {
            Runaway();
            return;
        }

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

        if(SearchCat(currentLocation))
            return;

        EnterInHole(currentLocation);
        EatCheese(currentLocation);
    }

    private void Runaway()
    {
        Location currentLocation = GetCurrentLocation();
        RemovePerception("run("+  AgentName + ","+ currentLocation.x + "," + currentLocation.y + ")");

        if(currentLocation.x == _nearestHole.x && currentLocation.y == _nearestHole.y)
        {
            EnterInHole(currentLocation);
            return;
        }

        if(currentLocation.x > _nearestHole.x)
        {
            currentLocation.x--;
        } else if (currentLocation.x < _nearestHole.x) {
            currentLocation.x++;
        }

        if(currentLocation.y > _nearestHole.y)
        {
            currentLocation.y--;
        } else if (currentLocation.y < _nearestHole.y) {
            currentLocation.y++;
        }

        AddAgentPerception("run("+  AgentName + ","+ currentLocation.x + "," + currentLocation.y + ")");
        SetAgentPosition(currentLocation);
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

        if(_cheeseAgentId >= 11 && _cheeseAgentId <= 19)
            return;

        RemoveMovementPerception(currentLocation);
        CheesePerception = Literal.parseLiteral("eat("+ AgentName + "," + _cheeseAgentId + ")");
        Model.Environment.addPercept(AgentName, CheesePerception);
    }

    private boolean SearchCat(Location currentLocation) {
        boolean foundCat = false;

        for(int catId : CatsId)
        {
            Location location = Model.getAgPos(catId);
            int x = location.x - currentLocation.x;
            int y = location.y - currentLocation.y;


            if((x >= -2 && x <= 2) && (y >= -2 && y <= 2))
            {
                foundCat = true;
            }
        }

        if(!foundCat)
            return foundCat;

        int nearestDistance = -1;
        _nearestHole = currentLocation;

        for(Location location : HolesLocation)
        {
            int distance = currentLocation.distanceManhattan(location);
            if(nearestDistance == -1 || distance < nearestDistance)
            {
                nearestDistance = distance;
                _nearestHole = location;
            }
        }

        RemoveMovementPerception(currentLocation);
        AddAgentPerception("run("+  AgentName + ","+ currentLocation.x + "," + currentLocation.y + ")");

        return foundCat;
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