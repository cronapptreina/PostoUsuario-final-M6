package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class PostoExt {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @return Var
	 */
	// MarcaFIPE
	public static Var buscar() throws Exception {
		return new Callable<Var>() {

			private Var consulta = Var.VAR_NULL;
			private Var excep = Var.VAR_NULL;

			public Var call() throws Exception {
				System.out.println(Var.valueOf("Buscando Postos Externos\n").getObjectAsString());
				try {
					consulta = cronapi.json.Operations.toJson(cronapi.util.Operations.getURLFromOthers(
							Var.valueOf("GET"), Var.valueOf("application/json"),
							Var.valueOf("https://postoemp.cronapp.io/api/cronapi/query/listapostos"), Var.VAR_NULL,
							Var.VAR_NULL));
				} catch (Exception excep_exception) {
					excep = Var.valueOf(excep_exception);
					consulta = cronapi.json.Operations.createObjectJson();
					cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.notify"),
							Var.valueOf("error"),
							Var.valueOf("ão foi possível acessar a lista de Postos. Tente mais tarde"));
				} finally {
				}
				System.out.println(consulta.getObjectAsString());
				return consulta;
			}
		}.call();
	}

}
