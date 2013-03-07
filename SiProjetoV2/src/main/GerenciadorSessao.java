package main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import exception.SessaoInexistenteException;
import exception.SessaoInvalidaException;

public class GerenciadorSessao {
	//<IDsessao , Login>
	private Map<String, String> sessoesAbertas;
	
	public GerenciadorSessao(){
		this.sessoesAbertas = new HashMap<String, String>();
	}
	
	public String abrirSessao(String login) {
		Sessao sessaoAberta = new Sessao(login);
		sessoesAbertas.put(sessaoAberta.getId(), login);
		return sessaoAberta.getId();
	}

	public String getLoginUser(String idSessao) throws SessaoInexistenteException, SessaoInvalidaException {
		if(idSessao==null || idSessao.trim().isEmpty()){
			throw new SessaoInvalidaException();
		}
		if (!sessoesAbertas.containsKey(idSessao)) {
			throw new SessaoInexistenteException();
		}
		return sessoesAbertas.get(idSessao);
	}

	public void encerrarSessao(String login) {
		String idSessao = procuraIdSessao(login);
		if(idSessao!=null){
			sessoesAbertas.remove(idSessao);
		}
		
	}
	//Metodo Auxiliar
	public String procuraIdSessao(String login){
		Iterator<Entry<String, String>> it = sessoesAbertas.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> pairs = it.next();
			if (pairs.getValue().equals(login)) {
				return pairs.getKey();
			}
		}
		return null;
	}
}
