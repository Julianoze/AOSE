import jason.asSyntax.*;
import jason.environment.Environment;

public class Ambiente5 extends Environment {

	public static final Term pc = Literal.parseLiteral("proximaCasa");
	public static final Term pcg = Literal.parseLiteral("proximaCasaGato");

	private EnvironmentModel model;
    private VisaoAmbiente  visao;

	/* Chamado antes da execu��o da MAS como os argumentos informados em .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);

        model = new EnvironmentModel(10, 10, Env.TotalAgentes(), this);

        visao  = new VisaoAmbiente(model);

        model.setView(visao);

    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        informAgsEnvironmentChanged();

        if (action.equals(pc)) {
        	model.proximaCasa();
        }

        if (action.equals(pcg)) {
        	model.proximaCasaGato();
        }
        return true;
    }

 /* Chamado antes do fim da execu��o do MAS */
    @Override
    public void stop() {
        super.stop();
    }
}