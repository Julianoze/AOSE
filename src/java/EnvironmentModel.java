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
			Agents.add(new Hole(this, 2));
			Agents.add(new Hole(this, 3));
			Agents.add(new Hole(this, 4));
			Agents.add(new Hole(this, 5));

			List<Location> holesLocation = new ArrayList<Location>();
			holesLocation.add(getAgPos(2));
			holesLocation.add(getAgPos(3));
			holesLocation.add(getAgPos(4));
			holesLocation.add(getAgPos(5));

			// TODO Add more two instances
			Agents.add(new Cat(this, 6));

			// TODO Add more nine instaces
			Agents.add(new Mouse(this, 10, holesLocation));

			// TODO Add one dog instance
			// TODO Add one deliveryMan instance
			// TODO Add twelve cheese instances
			// TODO Add door instace
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