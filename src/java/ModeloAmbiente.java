import jason.asSyntax.*;

import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

import java.util.logging.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.util.Random;


public class ModeloAmbiente extends GridWorldModel {		// Classe de modelo
    private Location donaCasaLoc, gatoLoc, ratoLoc, buracoUmLoc, buracoDoisLoc, buracoTresLoc, buracoQuatroLoc;
    private Environment Environment;

    public ModeloAmbiente (int arg0, int arg1, int arg2, Environment environment) {	// Recebe a coluna, linha e agente
	    super(arg0, arg1, arg2);

        Environment = environment;
	     try {
	        setAgPos(Env.IdDonaDeCasa(), 0, 0);								// Posiciona o primeiro agente na posi��o 0,0
	        setAgPos(Env.IdGatoUm(), 9, 9);
	        setAgPos(Env.IdRatoUm(), 7, 1);

			Random gerador = new Random();
			setAgPos(Env.IdBuracoUm(), gerador.nextInt(10), gerador.nextInt(10));
			setAgPos(Env.IdBuracoDois(), gerador.nextInt(10), gerador.nextInt(10));
			setAgPos(Env.IdBuracoTres(), gerador.nextInt(10), gerador.nextInt(10));
			setAgPos(Env.IdBuracoQuatro(), gerador.nextInt(10), gerador.nextInt(10));

	    	donaCasaLoc = getAgPos(Env.IdDonaDeCasa());
	    	gatoLoc = getAgPos(Env.IdGatoUm());
	    	ratoLoc = getAgPos(Env.IdRatoUm());
			buracoUmLoc = getAgPos(Env.IdBuracoUm());
			buracoDoisLoc = getAgPos(Env.IdBuracoDois());
			buracoTresLoc = getAgPos(Env.IdBuracoTres());
			buracoQuatroLoc = getAgPos(Env.IdBuracoQuatro());
			Literal pos1 = Literal.parseLiteral("pos(donaCasa,0,0)");
        	Environment.addPercept(pos1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void proximaCasa() {
       	Location donaCasaLoc = getAgPos(0);
      	int colunaDona = donaCasaLoc.x;
      	int ratoAchado = 0;

       	for (int coluna = colunaDona; coluna <= (colunaDona + 3); coluna++) {
        	if ((coluna == ratoLoc.x) && (donaCasaLoc.y == ratoLoc.y)) {
        		ratoAchado = 1;
        	    Literal ratoPercebido = Literal.parseLiteral("ratoPercebido(" + ratoLoc.x +"," + ratoLoc.y + ")");
        	    Environment.addPercept(ratoPercebido);
        	}
       	}

       	if (ratoAchado == 0) {
           	donaCasaLoc.x++;
			System.out.println("Keeping moving");
			System.out.println(donaCasaLoc.x);
			System.out.println(donaCasaLoc.y);

           	if (donaCasaLoc.x == 10) {
           		donaCasaLoc.x = 0;
           		donaCasaLoc.y++;
           	}
           	if (donaCasaLoc.y == 10) {
           	    return;
           	}
			System.out.println("App percept");

           	setAgPos(0, donaCasaLoc);
            Literal pos1 = Literal.parseLiteral("pos(donaCasa," + donaCasaLoc.x + "," + donaCasaLoc.y + ")");
            Environment.addPercept(pos1);
       	}
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