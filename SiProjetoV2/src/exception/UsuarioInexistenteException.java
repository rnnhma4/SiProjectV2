package exception;

public class UsuarioInexistenteException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public UsuarioInexistenteException() {
		super("Usu�rio inexistente");
	}

}
