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

	private int CheeseCount = 0;
	private int MouseCount = 0;

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

			List<Integer> catsId = new ArrayList<Integer>();

			for(int i = 8; i <= 10; i++)
			{
				Agents.add(new Cat(this, i, holesLocation));
				catsId.add(i);
			}

			List<Location> cheeseLocation = new ArrayList<Location>();
			for(int i = 20; i <= 31; i++)
			{
				Agents.add(new Cheese(this, i));
				cheeseLocation.add(getAgPos(i));
				CheeseCount++;
			}

			for(int i = 11; i <= 19; i++)
			{
				Agents.add(new Mouse(this, i, holesLocation, cheeseLocation, catsId));
				MouseCount++;
			}

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public boolean Action(String agentName, Structure action) {
        if(action.equals(Literal.parseLiteral("decreaseCheese")))
		{
			CheeseCount--;
			if(CheeseCount == 0)
				return false;
		}

        if(action.equals(Literal.parseLiteral("decreaseCheese")))
		{
			MouseCount--;
			if(MouseCount == 0)
				return false;
		}

		for(AgentInterface agent : Agents) {
			agent.Action(agentName, action);
        }

		return true;
	}
}