package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class BlocoAudit {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @param Dados
	 * @return Var
	 */
	// BlocoAudit
	public static Var afterInsert(Var Dados) throws Exception {
		return new Callable<Var>() {

			public Var call() throws Exception {
				cronapi.database.Operations.insert(Var.valueOf("app.entity.Logmsg"),
						Var.valueOf("stamp", cronapi.dateTime.Operations.getNow()),
						Var.valueOf("text", Var.valueOf(
								Var.valueOf("Usuário criado: ").toString() + formatar_usuario(Dados).toString())),
						Var.valueOf("user", getUserLogged()));
				return Var.VAR_NULL;
			}
		}.call();
	}

	/**
	 *
	 * @param Dados
	 */
	// Descreva esta função...
	public static void afterDelete(Var Dados) throws Exception {
		new Callable<Var>() {

			public Var call() throws Exception {
				cronapi.database.Operations.insert(Var.valueOf("app.entity.Logmsg"),
						Var.valueOf("stamp", cronapi.dateTime.Operations.getNow()),
						Var.valueOf("text", Var.valueOf(
								Var.valueOf("Usuário removido:").toString() + formatar_usuario(Dados).toString())),
						Var.valueOf("user", getUserLogged()));
				return Var.VAR_NULL;
			}
		}.call();
	}

	/**
	 *
	 * @param Dados
	 * @return Var
	 */
	// Descreva esta função...
	public static Var formatar_usuario(Var Dados) throws Exception {
		return new Callable<Var>() {

			private Var usu_format = Var.VAR_NULL;

			public Var call() throws Exception {
				usu_format = Var
						.valueOf(
								Var.valueOf("{{\"login\": \"").toString()
										+ cronapi.object.Operations.getObjectField(Dados, Var.valueOf("login"))
												.toString()
										+ Var.valueOf("\"}, {\"name: \"").toString()
										+ cronapi.object.Operations.getObjectField(Dados, Var.valueOf("name"))
												.toString()
										+ Var.valueOf("\"}, {\"email: \"").toString()
										+ cronapi.object.Operations.getObjectField(Dados, Var.valueOf("email"))
												.toString()
										+ Var.valueOf("\"}, {\"id: \"").toString()
										+ cronapi.object.Operations.getObjectField(Dados, Var.valueOf("id")).toString()
										+ Var.valueOf("\"}}").toString());
				System.out.println(usu_format.getObjectAsString());
				return usu_format;
			}
		}.call();
	}

	/**
	 *
	 * @return Var
	 */
	// Descreva esta função...
	public static Var getUserLogged() throws Exception {
		return new Callable<Var>() {

			private Var usu_logged = Var.VAR_NULL;
			private Var obj_usuario = Var.VAR_NULL;

			public Var call() throws Exception {
				usu_logged = cronapi.database.Operations.query(Var.valueOf("app.entity.User"),
						Var.valueOf("select u from User u where u.login = :login"),
						Var.valueOf("login", cronapi.util.Operations.getCurrentUserName()));
				obj_usuario = cronapi.object.Operations.newObject(Var.valueOf("app.entity.User"),
						Var.valueOf("id", cronapi.database.Operations.getField(usu_logged, Var.valueOf("this[0].id"))),
						Var.valueOf("email",
								cronapi.database.Operations.getField(usu_logged, Var.valueOf("this[0].email"))),
						Var.valueOf("name",
								cronapi.database.Operations.getField(usu_logged, Var.valueOf("this[0].name"))),
						Var.valueOf("login",
								cronapi.database.Operations.getField(usu_logged, Var.valueOf("this[0].login"))),
						Var.valueOf("password",
								cronapi.database.Operations.getField(usu_logged, Var.valueOf("this[0].password"))));
				return obj_usuario;
			}
		}.call();
	}

	/**
	 *
	 * @param Dados
	 */
	// Descreva esta função...
	public static void onNavigate(Var Dados) throws Exception {
		new Callable<Var>() {

			public Var call() throws Exception {
				if (cronapi.logic.Operations
						.isNull(cronapi.object.Operations.getObjectField(Dados, Var.valueOf("theme")))
						.getObjectAsBoolean()) {
					cronapi.object.Operations.setObjectField(Dados, Var.valueOf("theme"), Var.valueOf("tema básico"));
				}
				return Var.VAR_NULL;
			}
		}.call();
	}

}
