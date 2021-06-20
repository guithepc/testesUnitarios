package br.servicos;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING) //testa em ordem alfabética
public class OrdemTeste {

	public static int contadorDeTestes = 0;
	
	
	@Test
	public void inicia() {
		contadorDeTestes = 1;
	}
	
	@Test
	public void verifica() {
		Assert.assertEquals(1, contadorDeTestes);
	}
	

}
