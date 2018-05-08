package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class BlocoClienteCRUD {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @param Dados
	 * @return Var
	 */
	// BlocoClienteCRUD
	public static Var create(Var Dados) throws Exception {
		return new Callable<Var>() {

			public Var call() throws Exception {
				cronapi.database.Operations.insert(Var.valueOf("app.entity.Cliente"), Dados);
				return Dados;
			}
		}.call();
	}

	/**
	 *
	 * @param cliente_id
	 * @return Var
	 */
	// Descreva esta função...
	public static Var delete(Var cliente_id) throws Exception {
		return new Callable<Var>() {

			public Var call() throws Exception {
				cronapi.database.Operations.execute(Var.valueOf("app.entity.Cliente"),
						Var.valueOf("delete from Cliente where id = :id"), Var.valueOf("id", cliente_id));
				return Var.VAR_NULL;
			}
		}.call();
	}

	/**
	 *
	 * @return Var
	 */
	// Descreva esta função...
	public static Var retrieve() throws Exception {
		return new Callable<Var>() {

			private Var dadosCliente = Var.VAR_NULL;

			public Var call() throws Exception {
				dadosCliente = cronapi.database.Operations.queryPaged(Var.valueOf("app.entity.Cliente"),
						Var.valueOf("select c from Cliente c"), Var.valueOf(true));
				return dadosCliente;
			}
		}.call();
	}

	/**
	 *
	 * @param Dados
	 * @return Var
	 */
	// Descreva esta função...
	public static Var update(Var Dados) throws Exception {
		return new Callable<Var>() {

			public Var call() throws Exception {
				cronapi.database.Operations.update(Var.valueOf("app.entity.Cliente"), Dados);
				return Dados;
			}
		}.call();
	}

}
