package main;

import java.util.UUID;

public class Som {
	private String donoDoSom, link, id, dataCriacao;
	public Som(String login, String link, String dataCriacao) {
		this.donoDoSom = login;
		this.link = link;
		this.dataCriacao = dataCriacao;
		this.id =  UUID.randomUUID().toString().replace("-", "");
		
	}

	public String getId() {
		return id;
	}

	public String getDonoDoSom() {
		return donoDoSom;
	}

	public String getLink() {
		return link;
	}

	public String getDataCriacao() {
		return dataCriacao;
	}

}
