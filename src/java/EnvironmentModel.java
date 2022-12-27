import jason.asSyntax.*;

import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

import java.util.Random;

public class EnvironmentModel extends GridWorldModel {		// Classe de modelo
    private Location buracoUmLoc, buracoDoisLoc, buracoTresLoc, buracoQuatroLoc;
    protected Environment Environment;

	private HouseWife _houseWife;
	private Cat _cat;
	private Mouse _mouse;

    public EnvironmentModel (int arg0, int arg1, int arg2, Environment environment) {	// Recebe a coluna, linha e agente
	    super(arg0, arg1, arg2);

        Environment = environment;
	     try {
			_houseWife = new HouseWife(this);
			_cat = new Cat(this, 1);
			_mouse = new Mouse(this, 2);


			Random gerador = new Random();
			setAgPos(Env.IdBuracoUm(), gerador.nextInt(10), gerador.nextInt(10));
			setAgPos(Env.IdBuracoDois(), gerador.nextInt(10), gerador.nextInt(10));
			setAgPos(Env.IdBuracoTres(), gerador.nextInt(10), gerador.nextInt(10));
			setAgPos(Env.IdBuracoQuatro(), gerador.nextInt(10), gerador.nextInt(10));

			buracoUmLoc = getAgPos(Env.IdBuracoUm());
			buracoDoisLoc = getAgPos(Env.IdBuracoDois());
			buracoTresLoc = getAgPos(Env.IdBuracoTres());
			buracoQuatroLoc = getAgPos(Env.IdBuracoQuatro());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public void MoveHouseWife() {
		_houseWife.Move();
	}

	public void MoveCat() {
		_cat.Move();
	}

	public void MoveMouse() {
		_mouse.Move();
	}
}