import jason.asSyntax.*;
import jason.environment.grid.Location;
import java.util.Random;

public class AgentBase {
    protected EnvironmentModel Model;

    protected int AgentId;
    protected String AgentName;

    public AgentBase(EnvironmentModel model) {
        Model = model;
    }

    protected void SetRandomInitialAgentPosition() {
		Random random = new Random();
        int x, y;

        do {
            x = random.nextInt(10);
            y = random.nextInt(10);
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
        // TODO handle obstacle
        Location currentLocation = GetCurrentLocation();
        RemoveMovementPerception(currentLocation);

        currentLocation.x++;

        if (currentLocation.x == Model.getHeight()) {
        	currentLocation.x = 0;
        	currentLocation.y++;
        }
        if (currentLocation.y == Model.getWidth()) {
            return;
        }

        SetAgentPosition(currentLocation);
       	AddMovementPerception(currentLocation);
    }

    protected void MoveRandomic() {
		Random random = new Random();
        Location currentLocation = GetCurrentLocation();
        RemoveMovementPerception(currentLocation);

        boolean findPosition = true;

        do {
            int axiosX = currentLocation.x + (random.nextBoolean() ? 1 : -1);
            int axiosY = currentLocation.y + (random.nextBoolean() ? 1 : -1);

            if(axiosX == Model.getHeight())
                axiosX = currentLocation.x;

            if(axiosY == Model.getWidth())
                axiosY = currentLocation.y;

            if(Model.isFree(axiosX, axiosY))
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
}