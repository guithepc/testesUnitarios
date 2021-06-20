package br.servicos;


import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.utils.DataUtils;
import br.entidades.Filme;
import br.entidades.Locacao;
import br.entidades.Usuario;
import br.exceptions.FilmeSemEsqtoqueException;
import br.exceptions.LocadoraException;

public class LocacaoServiceTest {
	
	private LocacaoService service;
	
	private static int contador = 0;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup() {
		service = new LocacaoService();
		contador++;
		System.out.println(contador);
	}
	
	@After
	public void tearDown() {
		System.out.println("After");
	}
	
	@BeforeClass
	public static void setupClass() {
		System.out.println("BeforeClass"); //antes de toda classe
	}
	
	@AfterClass
	public static void tearDownClass() {
		System.out.println("AfterClass");
	}
	
	@Test
	public void testeJUnit() throws Exception {
		//cenário
		

		Usuario usuario = new Usuario("Nigui");
		Filme filme = new Filme("Indiana Jones", 1, 5.0);
		
		//ação
		
		Locacao locacao = service.alugarFilme(usuario, filme);
		
		
		//verificação
		
		//usando imports estáticos (Ctrl + Shift + M)
		//utilizando @Rule
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		
		//IMPORT não estático / estático
		Assert.assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
		assertThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
	}
	
	@Test(expected = FilmeSemEsqtoqueException.class)
	public void testLocacao_filmeSemEstoque() throws Exception {
		
		Usuario usuario = new Usuario("");
		Filme filme = new Filme("Indiana Jones", 0, 5.0);
		 
		service.alugarFilme(usuario, filme);
		
	}
	
	@Test
	public void testLocacao_filmeSemEstoque2() {
		
		Usuario usuario = new Usuario("Nigui");
		Filme filme = new Filme("Indiana Jones", 1, 5.0);
		
		try {
			service.alugarFilme(usuario, filme);
			//Assert.fail("Deveria lançar Exception"); //Me avisa que não falhou
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.assertThat(e.getMessage(), is("Filme sem estoque"));
		}
	}
	
	@Test
	public void testLocacao_filmeSemEstoque3() throws FilmeSemEsqtoqueException, LocadoraException {
		
		Usuario usuario = new Usuario("Nigui");
		Filme filme = new Filme("Indiana Jones", 0, 5.0);
		
		exception.expect(FilmeSemEsqtoqueException.class); //Aqui espero uma exception
		exception.expectMessage("Filme sem estoque"); 
		
		service.alugarFilme(usuario, filme);
		
		
		
	}
	
	@Test
	public void testeLocacao_usuarioVazio() throws FilmeSemEsqtoqueException {
		
		
		Filme filme = new Filme("Indiana Jones", 1, 5.0);
		
			
		try {
			service.alugarFilme(null, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), is("Usuário vazio"));
		}
			
	}
	
	@Test
	public void testLocacao_filmeVazio() throws FilmeSemEsqtoqueException, LocadoraException {
		
		Usuario usuario = new Usuario("User");
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		service.alugarFilme(usuario, null);
		
		
		
	}
}





