import jason.environment.grid.GridWorldView;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class VisaoAmbiente extends GridWorldView {
    public VisaoAmbiente(EnvironmentModel model) {

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
				rotulo = new String ("H");
				break;
			}
			case 1: {
				c = Color.red;
				rotulo = new String("D");
				break;
			}
			case 8, 9, 10: {
				c = Color.yellow;
				rotulo = new String ("C");
				break;
			}
			case 11, 12, 13, 14, 15, 16, 17, 18, 19: {
				c = Color.gray;
				rotulo = new String ("M");
				break;
			}
			case 4, 5, 6, 7: {
				c = Color.black;
				break;
			}

			case 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31: {
				c = Color.orange;
				rotulo = new String ("X");
				break;
			}
		}

		if (id >= 0 && id < Env.CountAgents()) {
			super.drawAgent(g, x, y, c, -1);

			g.setColor(Color.black);
			super.drawString(g, x, y, defaultFont, rotulo);
			setVisible(true);
		}
    }
}

