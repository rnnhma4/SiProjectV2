package main;

import java.util.List;

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

public class Sistema {
	private GerenciadorUsuario gerenciaUsuario = new GerenciadorUsuario();
	private GerenciadorSons gerenciaSons = new GerenciadorSons();
	private GerenciadorSessao gerenciaSessao = new GerenciadorSessao();

	
	/**
	 * Zera o Sistema
	 */
	public void zerarSistema() {
		gerenciaUsuario = new GerenciadorUsuario();
		gerenciaSons = new GerenciadorSons();
		gerenciaSessao = new GerenciadorSessao();
	}

	/**
	 * Cria Usuario
	 * 
	 * @param login
	 * @param senha
	 * @param nome
	 * @param email
	 * @throws CadastraException 
	 * @throws CamposException 
	 */
	public void criarUsuario(String login, String senha, String nome,
			String email) throws CamposException, CadastraException {
		gerenciaUsuario.criarUsuario(login, senha, nome, email);
	}

	/**
	 * abrirSessao
	 * 
	 * @param login
	 * @param senha
	 * @throws UsuarioInexistenteException 
	 * @throws CamposException 
	 */
	public String abrirSessao(String login, String senha) throws UsuarioInexistenteException, CamposException {
		if (gerenciaUsuario.verificaCadastro(login, senha)) {
			return gerenciaSessao.abrirSessao(login);
		}
		return null;

	}

	/**
	 * Retorna atributo do usuario
	 * @param login
	 * @param atributo
	 * @return
	 * @throws CamposException 
	 * @throws AtributoInexistenteException 
	 * @throws AtributoInvalidoException 
	 * @throws UsuarioInexistenteException 
	 */
	public String getAtributoUsuario(String login, String atributo) throws AtributoInvalidoException, AtributoInexistenteException, CamposException, UsuarioInexistenteException {
		return gerenciaUsuario.getAtributoUsuario(login, atributo);

	}

	/**
	 * Retorna a lista de PerfilMusical
	 * @param idSessao
	 * @return
	 * @throws SessaoInexistenteException 
	 * @throws SessaoInvalidaException 
	 */
	public List getPerfilMusical(String idSessao) throws SessaoInexistenteException, SessaoInvalidaException {
		String login = gerenciaSessao.getLoginUser(idSessao);
		return gerenciaUsuario.getPerfilMusical(login);
	}

	/**
	 * Posta Som
	 * @param idSessao
	 * @param link
	 * @param dataCriacao
	 * @return
	 * @throws PostException 
	 * @throws SessaoInexistenteException 
	 * @throws SessaoInvalidaException 
	 */
	public String postarSom(String idSessao, String link, String dataCriacao) throws PostException, SessaoInexistenteException, SessaoInvalidaException {
		String login = gerenciaSessao.getLoginUser(idSessao);
		Som som = gerenciaSons.criarSom(login, link, dataCriacao);
		gerenciaUsuario.postarSom(login, som.getId());
		return som.getId();
	}

	/**
	 * Retorna Atributo do Som
	 * @param idSom
	 * @param atributo
	 * @return
	 * @throws PostException 
	 * @throws AtributoInexistenteException 
	 * @throws AtributoInvalidoException 
	 */
	public String getAtributoSom(String idSom, String atributo) throws AtributoInvalidoException, AtributoInexistenteException, PostException {
		return gerenciaSons.getAtributoSom(idSom, atributo);
	}
	
	/**
	 * Encerra sessão
	 * @param login
	 */
	public void encerrarSessao(String login) {
		gerenciaSessao.encerrarSessao(login);		
	}

	/**
	 * Encerra Sistema
	 */
	public void encerrarSistema() {
		// TODO 
		//System.exit(0);
	}

	/**
	 * Retorna idUsuario de um user de determinada sessao
	 * @param idSessao
	 * @return
	 * @throws SessaoInexistenteException 
	 * @throws SessaoInvalidaException 
	 */
	public String getIDUsuario(String idSessao) throws SessaoInexistenteException, SessaoInvalidaException {
		String login =gerenciaSessao.getLoginUser(idSessao);
		return gerenciaUsuario.getIDUsuario(login);
	}

	/**
	 * Retorna numero de seguidores de um usuario de uma determinada sessao
	 * @param idSessao
	 * @return
	 * @throws SessaoInexistenteException 
	 * @throws SessaoInvalidaException 
	 */
	public int getNumeroDeSeguidores(String idSessao) throws SessaoInexistenteException, SessaoInvalidaException {
		String login = gerenciaSessao.getLoginUser(idSessao);
		return gerenciaUsuario.getNumeroDeSeguidores(login);
	}

	/**
	 * Faz o Usuario de uma determinada Sessao
	 * Seguir outro usuario
	 * @param idSessao
	 * @param login
	 * @throws SessaoInexistenteException 
	 * @throws SessaoInvalidaException 
	 * @throws CamposException 
	 * @throws LoginException 
	 */
	public void seguirUsuario(String idSessao, String login) throws SessaoInexistenteException, SessaoInvalidaException, CamposException, LoginException {
		String login1 = gerenciaSessao.getLoginUser(idSessao);
		String login2 = login;
		gerenciaUsuario.seguirUsuario(login1,login2);
		
	}
	/**
	 * Retorna lista das fontes de Sons
	 * @param idSessao
	 * @return
	 * @throws SessaoInexistenteException
	 * @throws SessaoInvalidaException
	 */
	public List getFontesDeSons(String idSessao) throws SessaoInexistenteException, SessaoInvalidaException {
		String login = gerenciaSessao.getLoginUser(idSessao);
		return gerenciaUsuario.getFontesDeSons(login);		
	}

	/**
	 * @param idSessao
	 * @return
	 * @throws SessaoInexistenteException
	 * @throws SessaoInvalidaException
	 */
	public List getListaDeSeguidores(String idSessao) throws SessaoInexistenteException, SessaoInvalidaException {
		String login = gerenciaSessao.getLoginUser(idSessao);
		return gerenciaUsuario.getListaDeSeguidores(login);
	}

	public List getVisaoDosSons(String idSessao) throws SessaoInexistenteException, SessaoInvalidaException {
		//Vou pensar em um modo melhor		
		String login = gerenciaSessao.getLoginUser(idSessao);
		return gerenciaUsuario.getVisaoDosSons(login);
	}

}
