import jason.asSyntax.*;

import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class EnvironmentModel extends GridWorldModel {		// Classe de modelo
    private Location buracoUmLoc, buracoDoisLoc, buracoTresLoc, buracoQuatroLoc;
    protected Environment Environment;

	private ArrayList<AgentInterface> Agents;

    public EnvironmentModel (int arg0, int arg1, int arg2, Environment environment) {	// Recebe a coluna, linha e agente
	    super(arg0, arg1, arg2);

        Environment = environment;
	     try {
			Agents = new ArrayList<AgentInterface>();

			Agents.add(new HouseWife(this));
			Agents.add(new Cat(this, 1));
			Agents.add(new Hole(this, 3));
			Agents.add(new Hole(this, 4));
			Agents.add(new Hole(this, 5));
			Agents.add(new Hole(this, 6));

			List<Location> holesLocation = new ArrayList<Location>();
			holesLocation.add(getAgPos(3));
			holesLocation.add(getAgPos(4));
			holesLocation.add(getAgPos(5));
			holesLocation.add(getAgPos(6));

			Agents.add(new Mouse(this, 2, holesLocation));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public void Action(String agentName, Structure action) {
        for(AgentInterface agent : Agents) {
			agent.Action(agentName, action);
        }
	}
}