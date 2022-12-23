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
        AddPercept("position("+ AgentName + "," + location.x + "," + location.y + ")");
    }

    protected void AddAgentPercept(String perception) {
        Model.Environment.addPercept(AgentName, Literal.parseLiteral(perception));
    }

    protected void SetAgentPosition(Location currentLocation) {
        Model.setAgPos(AgentId, currentLocation);
    }

    protected Location GetCurrentLocation() {
       	return Model.getAgPos(AgentId);
    }
}