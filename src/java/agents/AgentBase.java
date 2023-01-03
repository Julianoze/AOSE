import jason.asSyntax.*;
import jason.environment.grid.Location;
import java.util.Random;

public abstract class AgentBase implements AgentInterface {
    protected EnvironmentModel Model;

    protected int AgentId;
    protected String AgentName;
    protected boolean MoveBack;

    protected Random Random;

    public AgentBase(EnvironmentModel model) {
        Model = model;
        Random = new Random();
    }

    protected void SetRandomInitialAgentPosition() {
        int x, y;

        do {
            x = Random.nextInt(10);
            y = Random.nextInt(10);
        }
        while(!Model.isFree(x, y));

        Model.setAgPos(AgentId, x, y);
    }

    protected void SetInitialAgentPosition(int x, int y) {
        Model.setAgPos(AgentId, x, y);
    }

    protected void AddMovementPerception(Location location) {
        Model.Environment.addPercept(AgentName, Literal.parseLiteral("position("+ AgentName + "," + location.x + "," + location.y + ")"));
    }

    protected void AddAgentPerception(String perception) {
        Model.Environment.addPercept(AgentName, Literal.parseLiteral(perception));
    }

    protected void RemovePerception(String perception) {
        Model.Environment.removePercept(AgentName, Literal.parseLiteral(perception));
    }

    protected void RemoveMovementPerception(Location location) {
        Model.Environment.removePercept(AgentName, Literal.parseLiteral("position("+ AgentName + "," + location.x + "," + location.y + ")"));
    }

    protected void SetAgentPosition(Location currentLocation) {
        Model.setAgPos(AgentId, currentLocation);
    }

    protected Location GetCurrentLocation() {
       	return Model.getAgPos(AgentId);
    }

    protected void MoveLinear() {
        int axios = MoveBack ? -1 : 1;
        Location currentLocation = GetCurrentLocation();

        currentLocation.x += axios;

        if (currentLocation.x == Model.getHeight()) {
        	currentLocation.x = 0;
        	currentLocation.y += axios;
        }

        if (!Model.isFree(currentLocation.x, currentLocation.y))
        {
            int currentY = currentLocation.y + axios;
            if(currentY != Model.getWidth())
            {
                currentLocation.y = currentY;
            }
        }

        SetAgentPosition(currentLocation);
       	AddMovementPerception(currentLocation);
    }

    protected void MoveRandomic() {
        Location currentLocation = GetCurrentLocation();
        RemoveMovementPerception(currentLocation);

        boolean findPosition = true;

        do {
            int axiosX = currentLocation.x + (Random.nextBoolean() ? 1 : -1);
            int axiosY = currentLocation.y;

            if(axiosX < 0 || axiosX == Model.getHeight())
                axiosX = currentLocation.x;

            if(Random.nextBoolean())
            {
                axiosY += (Random.nextBoolean() ? 1 : -1);
                if(axiosY < 0 || axiosY == Model.getWidth())
                    axiosY = currentLocation.y;

            }

            if(Model.isFree(axiosX, axiosY) || AllowedPositions(axiosX, axiosY))
            {
                currentLocation.x = axiosX;
                currentLocation.y = axiosY;
                findPosition = false;
            }
        }
        while(findPosition);

        SetAgentPosition(currentLocation);
       	AddMovementPerception(currentLocation);
    }

    protected boolean AllowedPositions(int x, int y)
    {
        return false;
    };
}