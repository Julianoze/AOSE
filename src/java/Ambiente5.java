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

public class Ambiente5 extends Environment {

	public static final Term pc = Literal.parseLiteral("proximaCasa");
	public static final Term pcg = Literal.parseLiteral("proximaCasaGato");

	private ModeloAmbiente modelo;
    private VisaoAmbiente  visao;

	/* Chamado antes da execu��o da MAS como os argumentos informados em .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);

        modelo = new ModeloAmbiente(10, 10 ,Env.TotalAgentes(), this);

        visao  = new VisaoAmbiente(modelo);

        modelo.setView(visao);

    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        informAgsEnvironmentChanged();

        if (action.equals(pc)) {
			System.out.println("movimentou");
        	modelo.proximaCasa();
        }

        if (action.equals(pcg)) {
        	modelo.proximaCasaGato();
        }
        return true;
    }

 /* Chamado antes do fim da execu��o do MAS */
    @Override
    public void stop() {
        super.stop();
    }


}