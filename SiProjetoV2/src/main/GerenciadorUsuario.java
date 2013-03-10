package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import exception.AtributoInexistenteException;
import exception.AtributoInvalidoException;
import exception.CadastraException;
import exception.CampoEmailException;
import exception.CampoLoginException;
import exception.CampoNomeException;
import exception.CampoSenhaException;
import exception.CamposException;
import exception.LoginException;
import exception.LoginInexistenteException;
import exception.UsuarioInexistenteException;

public class GerenciadorUsuario {
	// <Login | obj User>
	private Map<String, Usuario> usuariosList;
	// <idUsuario | login >
	private Map<String, String> usuariosListIdUser;

	/**
	 * Construtor
	 */
	public GerenciadorUsuario() {
		this.usuariosList = new HashMap<String, Usuario>();
		this.usuariosListIdUser = new HashMap<String, String>();
	}

	/**
	 * Criar Usuario
	 * 
	 * @param login
	 * @param senha
	 * @param nome
	 * @param email
	 * @throws CadastraException
	 * @throws CamposException
	 */
	public void criarUsuario(String login, String senha, String nome,String email) throws CamposException, CadastraException {
		
		verificaExcecoes(login, email, senha, nome);
		Usuario usuario = new Usuario(login, senha, nome, email);
		usuariosList.put(usuario.getLogin(), usuario);
		usuariosListIdUser.put(usuario.getId(), usuario.getLogin());
	}

	//Metodo auxiliar1
	private void verificaExcecoes(String login, String email, String senha,String nome) throws CamposException, CadastraException {

		verificaCampos(login, "l");
		verificaCampos(senha, "s");
		verificaCampos(nome, "n");
		verificaCampos(email, "e");

		Iterator<Entry<String, Usuario>> it = usuariosList.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, Usuario> pairs = it.next();
			if (pairs.getValue().getEmail().equals(email)) {
				throw new CadastraException(
						"Já existe um usuário com este email");
			} else if (pairs.getValue().getLogin().equals(login)) {
				throw new CadastraException(
						"Já existe um usuário com este login");
			}
		}

	}
	//Auxiliar2
	private void verificaCampos(String dado, String tipo) throws CamposException {
		if (dado == null || dado.trim().isEmpty()) {
			switch (tipo.toLowerCase()) {
			case "l":
				throw new CampoLoginException();
			case "s":
				throw new CampoSenhaException();
			case "n":
				throw new CampoNomeException();
			case "e":
				throw new CampoEmailException();
			}
		}
	}

	/**
	 * Verifica se esta cadastrado e senha correta
	 * 
	 * @param login
	 * @param senha
	 * @return
	 * @throws CamposException 
	 * @throws CampoSenhaException
	 */
	public boolean verificaCadastro(String login, String senha) throws UsuarioInexistenteException, CamposException {
		Usuario usuario = usuariosList.get(login);
		verificaCampos(login, "l");
		if (usuario != null) {
			if (usuario.getSenha().equals(senha)) {
				return true;
			}
			throw new CampoLoginException();
		}
		 throw new UsuarioInexistenteException();
	}

	/**
	 * Retorna o atributo do Usuario
	 * 
	 * @param login
	 * @param atributo
	 * @return
	 * @throws CamposException 
	 * @throws AtributoInvalidoException 
	 * @throws AtributoInexistenteException 
	 * @throws UsuarioInexistenteException 
	 */
	public String getAtributoUsuario(String login, String atributo) throws CamposException, AtributoInvalidoException, AtributoInexistenteException, UsuarioInexistenteException {
		Usuario usuario = usuariosList.get(login);
		verificaCampos(login, "l");
		if((usuario!=null)){

			if (atributo == null || atributo.trim().isEmpty()) {
				throw new AtributoInvalidoException();
			}
			else if (atributo.equals("nome")) {
				return usuario.getNome();
			} else if (atributo.equals("email")) {
				return usuario.getEmail();
			} else{
				throw new AtributoInexistenteException();
			}
		}else{
			throw new UsuarioInexistenteException();
		}
	}

	
	

	/**
	 * Retorna o Perfil Musical
	 * 
	 * @param login
	 * @return
	 */
	public List getPerfilMusical(String login) {
		Usuario usuario = usuariosList.get(login);
		return usuario.getPerfilMusical();
	}

	/**
	 * add idSom na lista perfil musical de usuario;
	 * 
	 * @param login
	 * @param som
	 */
	public void postarSom(String login, String idSom) {
		Usuario usuario = usuariosList.get(login);
		usuario.addPerfilMusical(idSom);
	}

	/**
	 * Retorna o ID de Usuario
	 * @return
	 */
	public String getIDUsuario(String login) {
		return usuariosList.get(login).getId();
	}

	/**
	 * Retorna o tamanho da lista de seguidores de um deterinado User
	 * @param login
	 * @return
	 */
	public int getNumeroDeSeguidores(String login) {
		return usuariosList.get(login).getNumeroDeSeguidores();
	}

	/**
	 * login1 passa a seguir login2 e login2 passa a ser seguido por login1
	 * @param login1
	 * @param login2
	 * @throws CamposException
	 * @throws LoginException
	 */
	public void seguirUsuario(String login1, String login2) throws CamposException, LoginException {
		verificaCampos(login2, "l");
		if(login1.equals(login2)){
			throw new CampoLoginException();
		}
		if(!usuariosList.containsKey(login2)){
			throw new LoginInexistenteException();
		}
		Usuario usuario1 = usuariosList.get(login1);
		Usuario usuario2 = usuariosList.get(login2);
		//user 1 passa a seguir user 2
		usuario1.addSeguindo(usuario2.getId());
		//User2 passa a ter user 1 como seguidor
		usuario2.addSeguidor(usuario1.getId());
		
	}

	public List getFontesDeSons(String login) {
		return usuariosList.get(login).getFonteDeSons();
	}

	/**
	 * Retorna a lista de seguidores do Usuario
	 * @param login
	 * @return
	 */
	public List getListaDeSeguidores(String login) {
		return usuariosList.get(login).getSeguidores();
	}

	/**
	 * Retorna uma lista das visoes de sons do Usuario
	 * @param login
	 * @return
	 */
	public List getVisaoDosSons(String login) {
		List<Som> visaoDosSons = new LinkedList();
		List<String> fonteDeSons = getFontesDeSons(login);
		//Tenho a lista de Users Minha fonte;
		//pego esse idUser acho login
		Iterator<String> itr = fonteDeSons.iterator();
		//visaoDosSons.addAll(getPerfilMusical(login));
	      while(itr.hasNext()) {
	    	  String login2 = usuariosListIdUser.get(itr.next());
	    	  visaoDosSons.addAll(0,getPerfilMusical(login2));
	      }
	      return visaoDosSons;
	}

	public List getSonsFavoritos(String login) {
		return usuariosList.get(login).getSonsFavoritos();
	}

	public List getFeedExtra(String login) {
		return  usuariosList.get(login).getFeedExtra();
	}

	public void favoritarSom(String login, String idSom) {
		/*
		 * add na lista de favoritos do usuario login
		 * e add no feed todos que é seguidor do user login;
		 */
			Usuario usuario = usuariosList.get(login);
			usuario.addSomFavorito(idSom);
			Iterator<String> itr = usuario.getSeguidores().iterator();
			while (itr.hasNext()){
				String loginUser = usuariosListIdUser.get(itr.next());
				usuariosList.get(loginUser).addFeedExtra(idSom);
			}
	}


}
