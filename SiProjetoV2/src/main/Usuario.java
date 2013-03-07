package main;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Usuario {

	private String login, senha, nome, email, id;
	private List<String> perfilMusical;
	private List<String> seguidores;
	private List<String> FontesDeSons;

	public Usuario(String login, String senha, String nome, String email) {
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
		this.id = UUID.randomUUID().toString().replace("-", "");
		this.perfilMusical = new LinkedList<String>();
		this.seguidores = new LinkedList<String>();
		this.FontesDeSons = new LinkedList<String>();
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getId() {
		return id;
	}

	/**
	 * Retorna o Perfil Musical []
	 * @return
	 */
	public List getPerfilMusical() {
		return perfilMusical;
	}
	
	/**
	 * Add na lista de Perfil Musical
	 */
	public void addPerfilMusical(String idSom){
		perfilMusical.add(0,idSom);
	}

	/**
	 * Retorna o tamanho da lista de Seguidores
	 * @return
	 */
	public int getNumeroDeSeguidores() {
		return seguidores.size();
	}
	/**
	 * add IDUser na lista de FonteDeSons
	 * @param idUser
	 */
	public void addSeguindo(String idUser) {
		FontesDeSons.add(idUser);		
	}
	/**
	 * add IdUser na lista de seguidor;
	 * @param idUser
	 */
	public void addSeguidor(String idUser) {
		seguidores.add(0,idUser);
	}
	/**
	 * Retorna a lista de Seguidor
	 * @return
	 */
	public List getSeguidores() {
		return seguidores;
	}
	/**
	 *Retorna a lista de FonteDeSons(Quem um user segue é uma fonte de som)
	 * @return
	 */
	public List getFonteDeSons() {
		return FontesDeSons;
	}
	
}
