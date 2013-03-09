package main;

import java.util.UUID;

public class Sessao {

	private String donoDaSessao, id;
	public Sessao(String login){
		this.donoDaSessao = login;
		this.id = UUID.randomUUID().toString().replace("-", "");
	}
	public String getDonoDaSessao() {
		return donoDaSessao;
	}
	public String getId() {
		return id;
	}
	
	
}
