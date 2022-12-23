import jason.asSyntax.*;
import jason.environment.grid.Location;
import java.util.Random;

public class AgentBase {
    protected EnvironmentModel Model;

    protected int AgentId;

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

        SetInitialAgentPosition(x, y);
    }

    protected void SetInitialAgentPosition(int x, int y) {
        Model.setAgPos(AgentId, x, y);
    }

    protected void AddPercept(Literal literal) {
        Model.Environment.addPercept(literal);
    }

    protected void SetAgentPosition(Location currentLocation) {
        Model.setAgPos(AgentId, currentLocation);
    }

    protected Location GetCurrentLocation() {
       	return Model.getAgPos(AgentId);
    }
}