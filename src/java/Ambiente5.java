import jason.asSyntax.*;
import jason.environment.Environment;

public class Ambiente5 extends Environment {
	private EnvironmentModel model;
    private VisaoAmbiente  visao;

	/* Chamado antes da execu��o da MAS como os argumentos informados em .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);

        model = new EnvironmentModel(10, 10, Env.CountAgents(), this);

        visao  = new VisaoAmbiente(model);

        model.setView(visao);

    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        informAgsEnvironmentChanged();

        if(!model.Action(agName, action))
            stop();

        return true;
    }

    /* Chamado antes do fim da execu��o do MAS */
    @Override
    public void stop() {
        super.stop();
    }
}