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


public class VisaoAmbiente extends GridWorldView {
    public VisaoAmbiente(ModeloAmbiente model) {

		super(model, "Mundo CasaInfestada", 700);

    	defaultFont = new Font("Arial", Font.BOLD, 12); // Muda a fonte padr�o

   		setVisible(true);

		repaint();
    }

    @Override
    public void drawAgent(Graphics g, int x, int y, Color c, int id) {
		String rotulo = "";

		switch (id) {
			case 0: {
				c = Color.green;
				rotulo = new String ("DonaCasa");
				break;
			}
			case 1: {
				c = Color.yellow;
				rotulo = new String ("Gato");
				break;
			}
			case 2: {
				c = Color.gray;
				rotulo = new String ("Rato");
				break;
			}
			case 3, 4, 5, 6: {
				c = Color.black;
				break;
			}
		}

		if (id >= 0 && id < Env.TotalAgentes()) {
			super.drawAgent(g, x, y, c, -1);

			g.setColor(Color.black);
			super.drawString(g, x, y, defaultFont, rotulo);
			setVisible(true);
		}
    }
}

