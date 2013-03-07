package exception;

public class CampoEmailException extends CamposException {
	/**
	 * Erro no campo Email (null ou vazio)
	 */
	private static final long serialVersionUID = 1L;

	public CampoEmailException() {
		super("Email inv�lido");
	}
	public CampoEmailException(String msgErro) {
		super(msgErro);
	}
}
