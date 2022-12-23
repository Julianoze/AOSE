import jason.asSyntax.*;
import jason.environment.grid.Location;

public class AgentBase {
    protected ModeloAmbiente Modelo;

    protected int AgentId;

    public AgentBase(ModeloAmbiente modelo) {
        Modelo = modelo;
    }

    protected void SetInitialAgentPosition(int x, int y) {
        Modelo.setAgPos(AgentId, x, y);
    }

    protected void AddPercept(Literal literal) {
        Modelo.Environment.addPercept(literal);
    }

    protected void SetAgentPosition(Location currentLocation) {
        Modelo.setAgPos(AgentId, currentLocation);
    }

    protected Location GetCurrentLocation() {
       	return Modelo.getAgPos(AgentId);
    }
}