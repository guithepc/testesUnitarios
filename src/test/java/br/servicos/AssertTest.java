package br.servicos;

import org.junit.Assert;
import org.junit.Test;

import br.entidades.Usuario;

public class AssertTest {

	@Test
	public void test () {
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		
		Assert.assertEquals("Erro de comparação", 1, 1); 
		Assert.assertEquals(0.66, 0.66, 0.01); //pede uma margem de erro (casas decimais)
		Assert.assertEquals(Math.PI, 3.14, 0.01);
		Assert.assertEquals(Math.PI, 3.141, 0.001);
		
		int i = 10;
		Integer i2 = 10;
		Assert.assertEquals(Integer.valueOf(i), i2); //tipo primitivo e objeto não rola, precisamos pegar o valor do primitivo
		Assert.assertEquals(i, i2.intValue());
		
		Assert.assertEquals("olá", "olá");
		Assert.assertNotEquals("olá", "ola");
		Assert.assertTrue("olá".equalsIgnoreCase("Olá"));
		Assert.assertTrue("shimeji".startsWith("shi"));
		
		Usuario u1 = new Usuario("Nome1");
		Usuario u2 = new Usuario("Nome1");
		Usuario u3 = null;
		
		Assert.assertEquals(u1, u2); //dessa forma,deve possuir o método equals implementado
		
		Assert.assertSame(u1, u1);
		Assert.assertNotSame(u1, u2);
		
		Assert.assertNull(u3);
		Assert.assertNotNull(u2);
		
	}
}
