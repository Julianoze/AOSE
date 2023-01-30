import jason.asSyntax.*;

import jason.environment.Environment;
import jason.environment.grid.Location;

import java.util.List;

public class Dog extends AgentBase {
    private List<Integer> CatsId;
    private int _catId;
    private boolean _isHunting;

    public Dog(EnvironmentModel model,
               List<Integer> catsId) {
        super(model);

        AgentId = 1;
        AgentName = "dog";

        CatsId = catsId;

        AddAgentPerception("sleep");
        SetRandomInitialAgentPosition();
    }

    public void Action(String agentName, Structure action) {
        if(!agentName.equals(AgentName))
            return;

        if(action.equals(Literal.parseLiteral("hunting")))
        {
            _isHunting = true;
            HuntCat();
            return;
        }

        Move();
    }

    private void HuntCat()
    {
        Location currentLocation = GetCurrentLocation();
        if(_catId == 0)
        {
            AddMovementPerception(GetCurrentLocation());
            return;
        }

        RemovePerception("huntingCat(" + currentLocation.x + "," + currentLocation.y + ")");

        Location catLocation = Model.getAgPos(_catId);

        if(isNearHousewife(catLocation))
        {
            Model.Environment.clearPercepts(AgentName);
            AddMovementPerception(currentLocation);
            return;
        }

        if(currentLocation.x == catLocation.x && currentLocation.y == catLocation.y)
        {
            Model.Environment.clearPercepts(AgentName);
            AddAgentPerception("catchedCat(" + _catId + ")");
            _isHunting = false;
            _catId = 0;
            return;
        }

        if(currentLocation.x > catLocation.x)
        {
            currentLocation.x--;
        } else if (currentLocation.x < catLocation.x) {
            currentLocation.x++;
        }

        if(currentLocation.y > catLocation.y)
        {
            currentLocation.y--;
        } else if (currentLocation.y < catLocation.y) {
            currentLocation.y++;
        }

        AddAgentPerception("huntingCat(" + currentLocation.x + "," + currentLocation.y + ")");
        SetAgentPosition(currentLocation);
    }

    public void Move() {
        _isHunting = false;
        Location currentLocation = GetCurrentLocation();
        if(SearchCat(currentLocation))
            return;

        MoveRandomic();
    }

    private boolean SearchCat(Location currentLocation) {
        boolean foundCat = false;
        Location catLocation = currentLocation;

        for(int catId : CatsId)
        {
            Location location = Model.getAgPos(catId);
            int x = location.x - currentLocation.x;
            int y = location.y - currentLocation.y;


            if((x >= -4 && x <= 4) && (y >= -4 && y <= 4))
            {
                _catId = catId;
                catLocation = location;
                foundCat = true;
            }
        }

        if(!foundCat)
            return foundCat;

        boolean nearHousewife = isNearHousewife(catLocation);
        if(!nearHousewife)
        {
            RemoveMovementPerception(currentLocation);
            AddAgentPerception("huntingCat(" + currentLocation.x + "," + currentLocation.y + ")");
        }

        return !nearHousewife;
    }

    private boolean isNearHousewife(Location catLocation)
    {
        Location houseWifeLocation = Model.getAgPos(0);

        int xAxis = catLocation.x - houseWifeLocation.x;
        int yAxis = catLocation.y - houseWifeLocation.y;

        if(xAxis < 0)
            xAxis *= -1;

        if(yAxis < 0)
            yAxis *= -1;

        return xAxis <= 1 || yAxis <= 1;
    }
}