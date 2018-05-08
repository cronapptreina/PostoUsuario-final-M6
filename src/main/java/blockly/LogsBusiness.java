package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class LogsBusiness {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @param Entidade
	 * @return Var
	 */
	// LogsBusiness
	public static Var onNavigate(Var Entidade) throws Exception {
		return new Callable<Var>() {

			public Var call() throws Exception {
				cronapi.object.Operations.setObjectField(Entidade, Var.valueOf("obs_admin"), Var.valueOf("texto obs"));
				return Var.VAR_NULL;
			}
		}.call();
	}

}
