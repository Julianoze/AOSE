import jason.asSyntax.*;

import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

import java.util.Random;

public class ModeloAmbiente extends GridWorldModel {		// Classe de modelo
    private Location donaCasaLoc, gatoLoc, ratoLoc, buracoUmLoc, buracoDoisLoc, buracoTresLoc, buracoQuatroLoc;
    protected Environment Environment;

	private HouseWife _houseWife;

    public ModeloAmbiente (int arg0, int arg1, int arg2, Environment environment) {	// Recebe a coluna, linha e agente
	    super(arg0, arg1, arg2);

        Environment = environment;
	     try {
			_houseWife = new HouseWife(this);

	        setAgPos(Env.IdGatoUm(), 9, 9);
	        setAgPos(Env.IdRatoUm(), 7, 1);

			Random gerador = new Random();
			setAgPos(Env.IdBuracoUm(), gerador.nextInt(10), gerador.nextInt(10));
			setAgPos(Env.IdBuracoDois(), gerador.nextInt(10), gerador.nextInt(10));
			setAgPos(Env.IdBuracoTres(), gerador.nextInt(10), gerador.nextInt(10));
			setAgPos(Env.IdBuracoQuatro(), gerador.nextInt(10), gerador.nextInt(10));

	    	gatoLoc = getAgPos(Env.IdGatoUm());
	    	ratoLoc = getAgPos(Env.IdRatoUm());
			buracoUmLoc = getAgPos(Env.IdBuracoUm());
			buracoDoisLoc = getAgPos(Env.IdBuracoDois());
			buracoTresLoc = getAgPos(Env.IdBuracoTres());
			buracoQuatroLoc = getAgPos(Env.IdBuracoQuatro());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public void proximaCasa() {
		_houseWife.proximaCasa();
	}

    public void proximaCasaGato() {

        gatoLoc = getAgPos(1);
        ratoLoc = getAgPos(2);

        if ((gatoLoc.x == ratoLoc.x) && (gatoLoc.y == ratoLoc.y)) {
            Literal ratoApanhado = Literal.parseLiteral("ratoApanhado");
            Environment.addPercept(ratoApanhado);
        }
        else {

        	if (ratoLoc.x > gatoLoc.x) {
           		gatoLoc.x++;
        	}

        	if (ratoLoc.x < gatoLoc.x) {
           		gatoLoc.x--;
        	}

        	if (ratoLoc.x == gatoLoc.x) {
        	   if (ratoLoc.y > gatoLoc.y) {
        		   gatoLoc.y++;
        	   }

        	   if (ratoLoc.y < gatoLoc.y) {
        		   gatoLoc.y--;
        	   }

        	}
        }

        setAgPos(1, gatoLoc);
        Literal perseguicao = Literal.parseLiteral("aindaNaoPegou (" + gatoLoc.x + ", " + gatoLoc.y + ")");
       	Environment.addPercept(perseguicao);
    }
}