package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import exception.AtributoInexistenteException;
import exception.AtributoInvalidoException;
import exception.PostDateException;
import exception.PostException;
import exception.PostSomInvalidException;

public class GerenciadorSons {
	//<idSom | som>
	private Map<String, Som> sonsList;

	public GerenciadorSons() {
		this.sonsList = new HashMap<String, Som>();
	}
/**
 * Cria um som e add no map de sons;
 * @param login
 * @param link
 * @param dataCriacao
 * @return
 * @throws PostException
 */
	public Som criarSom(String login, String link, String dataCriacao) throws PostException {
		verificaLink(link);
		verificaData(dataCriacao);
		Som som = new Som(login,link,dataCriacao);
		sonsList.put(som.getId(), som);
		return som;
	}
/**
 * Retorna atributo do som
 * @param idSom
 * @param atributo
 * @return
 * @throws AtributoInvalidoException 
 * @throws PostException 
 * @throws AtributoInexistenteException 
 */
	public String getAtributoSom(String idSom, String atributo) throws AtributoInvalidoException, PostException, AtributoInexistenteException {
		verificaId(idSom);
		verificaAtributo(atributo);
		Som som = sonsList.get(idSom);
		if(atributo.equals("dataCriacao")){
			return som.getDataCriacao();
		}
		throw new AtributoInexistenteException();
	}

	private void verificaId(String idSom) throws PostException {
		if (idSom == null || idSom.trim().isEmpty()) {
			throw new PostSomInvalidException();
		}
	}
	
	private void verificaAtributo(String atributo)
			throws AtributoInvalidoException {
		if (atributo == null || atributo.trim().isEmpty()) {
			throw new AtributoInvalidoException();
		}
	}
	
	/*
	 * Metodo Auxiliar verificar link valido;
	 */
	private void verificaLink(String link) throws PostSomInvalidException {
		if (link == null || link.trim().isEmpty()) {
			throw new PostSomInvalidException();
		}
	}
	/*
	 * Auxiliar verificar data valida;
	 */
	private void verificaData(String data) throws PostException {
		if (data == null || data.trim().isEmpty()) {
			throw new PostDateException();
		}
		// TODO
		SimpleDateFormat converte = new SimpleDateFormat("dd/MM/yyy");
		converte.setLenient(false);
		Date dataRecebida;
		try {
			// se passar por aqui, eh do tipo dd/mm/yyyy
			dataRecebida = converte.parse(data);
		} catch (ParseException e) {
			throw new PostDateException();
		} catch (Exception e) {
			throw new PostException("Erro desconhecido ao receber data");
		}
		Date dataAtual = new Date();
		if (dataRecebida.before(dataAtual)) {
			throw new PostDateException();
		}
	}

}
