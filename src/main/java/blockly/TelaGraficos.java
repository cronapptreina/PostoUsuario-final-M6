package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity(post = "Public", get = "Public", execute = "Public", delete = "Public", put = "Public")
public class TelaGraficos {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @return Var
	 */
	// TelaGraficos
	public static Var atualizaData() throws Exception {
		return new Callable<Var>() {

			public Var call() throws Exception {
				cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"),
						Var.valueOf("vars.dataAtual"),
						cronapi.dateTime.Operations.formatDateTime(cronapi.dateTime.Operations
								.incHour(cronapi.dateTime.Operations.getNow(), Var.valueOf(-3)),
								Var.valueOf("dd/MM/yyyy hh:mm:ss")));
				return Var.VAR_NULL;
			}
		}.call();
	}

	/**
	 */
	// Descreva esta função...
	public static void grafMarca() throws Exception {
		new Callable<Var>() {

			private Var gMarca = Var.VAR_NULL;

			public Var call() throws Exception {
				gMarca = cronapi.database.Operations.query(Var.valueOf("app.entity.Abastecimento"), Var.valueOf(
						"select a.carro.marcaFIPE.fipe_name, SUM(a.valor)/SUM(a.km) from Abastecimento a  group by a.carro.marcaFIPE.fipe_name"));
				cronapi.chart.Operations.createChart(Var.valueOf("grafMarca"), Var.valueOf("bar"),
						cronapi.database.Operations.getColumn(gMarca, Var.valueOf("this[0]")), Var.VAR_NULL,
						cronapi.database.Operations.getColumn(gMarca, Var.valueOf("this[1]")));
				return Var.VAR_NULL;
			}
		}.call();
	}

	/**
	 */
	// Descreva esta função...
	public static void grafPosto() throws Exception {
		new Callable<Var>() {

			private Var gPosto = Var.VAR_NULL;

			public Var call() throws Exception {
				gPosto = cronapi.database.Operations.query(Var.valueOf("app.entity.Abastecimento"), Var.valueOf(
						"select a.postoExt.name, SUM(a.valor)/SUM(a.km) from Abastecimento a  group by a.postoExt.name"));
				cronapi.chart.Operations.createChart(Var.valueOf("grafPosto"), Var.valueOf("doughnut"),
						cronapi.database.Operations.getColumn(gPosto, Var.valueOf("this[0]")), Var.VAR_NULL,
						cronapi.database.Operations.getColumn(gPosto, Var.valueOf("this[1]")));
				return Var.VAR_NULL;
			}
		}.call();
	}

	/**
	 */
	// Descreva esta função...
	public static void iniciar() throws Exception {
		new Callable<Var>() {

			public Var call() throws Exception {
				blockly.TelaGraficos.grafMarca();
				blockly.TelaGraficos.grafPosto();
				blockly.TelaGraficos.atualizaData();
				return Var.VAR_NULL;
			}
		}.call();
	}

}
