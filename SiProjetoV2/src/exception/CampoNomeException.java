package exception;

public class CampoNomeException extends CamposException {
	/**
	 * Erro no campo nome (null ou vazio)
	 */
	private static final long serialVersionUID = 1L;

	public CampoNomeException() {
		super("Nome inv�lido");
	}
	public CampoNomeException(String msgErro) {
		super(msgErro);
	}
}