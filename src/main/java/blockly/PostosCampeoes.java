package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class PostosCampeoes {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @return Var
	 */
	// PostosCampeoes
	public static Var listar() throws Exception {
		return new Callable<Var>() {

			private Var consulta = Var.VAR_NULL;
			private Var lista_retorno = Var.VAR_NULL;
			private Var valor_parcial = Var.VAR_NULL;

			public Var call() throws Exception {
				consulta = cronapi.database.Operations.query(Var.valueOf("app.entity.Abastecimento"),
						Var.valueOf(
								"select a.posto.nome, SUM(a.valor)/SUM(a.km) from Abastecimento a  group by a.posto.nome  order by SUM(a.valor)/SUM(a.km) asc"),
						Var.VAR_NULL);
				lista_retorno = cronapi.list.Operations.newList();
				while (cronapi.database.Operations.hasElement(consulta).getObjectAsBoolean()) {
					valor_parcial = cronapi.map.Operations.createObjectMapWith(
							Var.valueOf("posto",
									cronapi.database.Operations.getField(consulta, Var.valueOf("this[0]"))),
							Var.valueOf("custo_medio",
									cronapi.database.Operations.getField(consulta, Var.valueOf("this[1]"))));
					System.out.println(valor_parcial.getObjectAsString());
					cronapi.list.Operations.addLast(lista_retorno, valor_parcial);
					cronapi.database.Operations.next(consulta);
				} // end while
				System.out.println(lista_retorno.getObjectAsString());
				return lista_retorno;
			}
		}.call();
	}

}
