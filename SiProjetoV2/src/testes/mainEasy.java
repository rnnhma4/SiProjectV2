package testes;

import java.util.ArrayList;
import java.util.List;
import easyaccept.EasyAcceptFacade;

public class mainEasy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> files = new ArrayList<String>();
		files.add("testes/US01.txt");
		files.add("testes/US02.txt");
		files.add("testes/US03.txt");
		FacadeEasy facade = new FacadeEasy();
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(facade, files);
		eaFacade.executeTests();
		System.out.println(eaFacade.getCompleteResults());
	}

}
