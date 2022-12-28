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

			// TODO Add one dog instance (1)
			// TODO Add one deliveryMan instance (2)
			// TODO Add door instace (3)

			List<Location> holesLocation = new ArrayList<Location>();
			for(int i = 4; i <= 7; i++)
			{
				Agents.add(new Hole(this, i));
				holesLocation.add(getAgPos(i));
			}

			// TODO Add more two instances
			Agents.add(new Cat(this, 8));

			List<Location> cheeseLocation = new ArrayList<Location>();
			for(int i = 20; i <= 31; i++)
			{
				Agents.add(new Cheese(this, i));
				cheeseLocation.add(getAgPos(i));
			}


			// TODO Add more nine instaces
			Agents.add(new Mouse(this, 11, holesLocation, cheeseLocation));

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