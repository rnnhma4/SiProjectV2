package testes;


import exception.AtributoInexistenteException;
import exception.AtributoInvalidoException;
import exception.CadastraException;
import exception.CampoLoginException;
import exception.CampoSenhaException;
import exception.CamposException;
import exception.LoginException;
import exception.PostException;
import exception.SessaoInexistenteException;
import exception.SessaoInvalidaException;
import exception.UsuarioInexistenteException;
import main.Sistema;

public class FacadeEasy {

	Sistema sistema;

	public FacadeEasy(){
		sistema = new Sistema();
	}

	public void zerarSistema(){
		sistema.zerarSistema();
	}
	
	public void criarUsuario(String login, String senha, String nome, String email) throws CamposException, CadastraException{
		sistema.criarUsuario(login, senha, nome, email);
	}

	public String abrirSessao(String login, String senha) throws UsuarioInexistenteException, CamposException{
		return sistema.abrirSessao(login, senha);
	}
	
	public String getAtributoUsuario(String login, String atributo) throws AtributoInvalidoException, AtributoInexistenteException, CamposException, UsuarioInexistenteException{
		return sistema.getAtributoUsuario(login, atributo);
	}
	
	public String getPerfilMusical(String idSessao) throws SessaoInexistenteException, SessaoInvalidaException{
		return sistema.getPerfilMusical(idSessao).toString().replace("[", "{").replace("]", "}").replace(" ", "");
	}
	
	public String postarSom(String idSessao, String link, String dataCriacao) throws PostException, SessaoInexistenteException, SessaoInvalidaException{
		return sistema.postarSom(idSessao, link, dataCriacao);
	}
	
	public String getAtributoSom(String idSom, String atributo) throws AtributoInvalidoException, AtributoInexistenteException, PostException{
		return sistema.getAtributoSom(idSom, atributo);
	}
	
	public void encerrarSessao(String login){
		sistema.encerrarSessao(login);
	}
	
	public void encerrarSistema(){
		sistema.encerrarSistema();
	}
	/*
	 * US02
	 */
	public String getIDUsuario(String idSessao) throws SessaoInexistenteException, SessaoInvalidaException{
		return sistema.getIDUsuario(idSessao);
	}
	
	public int  getNumeroDeSeguidores(String idSessao) throws SessaoInexistenteException, SessaoInvalidaException{
		return sistema.getNumeroDeSeguidores(idSessao);
	}
	
	public void seguirUsuario(String idSessao, String login) throws SessaoInexistenteException, SessaoInvalidaException, CamposException, LoginException{
		sistema.seguirUsuario(idSessao, login);
	}
	
	public String getFontesDeSons(String idSessao) throws SessaoInexistenteException, SessaoInvalidaException{
		return sistema.getFontesDeSons(idSessao).toString().replace("[", "{").replace("]", "}").replace(" ", "");
	}
	
	public String getListaDeSeguidores(String idSessao) throws SessaoInexistenteException, SessaoInvalidaException{
		return sistema.getListaDeSeguidores(idSessao).toString().replace("[", "{").replace("]", "}").replace(" ", "");
	}
	
	public String getVisaoDosSons(String idSessao) throws SessaoInexistenteException, SessaoInvalidaException{
		return sistema.getVisaoDosSons(idSessao).toString().replace("[", "{").replace("]", "}").replace(" ", "");
	}
	
}
