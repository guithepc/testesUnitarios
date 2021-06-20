package br.servicos;


import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Assert;
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
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testeJUnit() throws Exception {
		//cen�rio
		
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Nigui");
		Filme filme = new Filme("Indiana Jones", 1, 5.0);
		
		//a��o
		
		Locacao locacao = service.alugarFilme(usuario, filme);
		
		
		//verifica��o
		
		//usando imports est�ticos (Ctrl + Shift + M)
		//utilizando @Rule
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		
		//IMPORT n�o est�tico / est�tico
		Assert.assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
		assertThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
	}
	
	@Test(expected = FilmeSemEsqtoqueException.class)
	public void testLocacao_filmeSemEstoque() throws Exception {
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("");
		Filme filme = new Filme("Indiana Jones", 0, 5.0);
		 
		service.alugarFilme(usuario, filme);
		
	}
	
	@Test
	public void testLocacao_filmeSemEstoque2() {
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Nigui");
		Filme filme = new Filme("Indiana Jones", 1, 5.0);
		
		try {
			service.alugarFilme(usuario, filme);
			//Assert.fail("Deveria lan�ar Exception"); //Me avisa que n�o falhou
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.assertThat(e.getMessage(), is("Filme sem estoque"));
		}
	}
	
	@Test
	public void testLocacao_filmeSemEstoque3() throws FilmeSemEsqtoqueException, LocadoraException {
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Nigui");
		Filme filme = new Filme("Indiana Jones", 0, 5.0);
		
		exception.expect(FilmeSemEsqtoqueException.class); //Aqui espero uma exception
		exception.expectMessage("Filme sem estoque"); 
		
		service.alugarFilme(usuario, filme);
		
		
		
	}
	
	@Test
	public void testeLocacao_usuarioVazio() throws FilmeSemEsqtoqueException {
		LocacaoService service = new LocacaoService();
		;
		Filme filme = new Filme("Indiana Jones", 1, 5.0);
		
			
		try {
			service.alugarFilme(null, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), is("Usu�rio vazio"));
		}
			
	}
	
	@Test
	public void testLocacao_filmeVazio() throws FilmeSemEsqtoqueException, LocadoraException {
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("User");
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		service.alugarFilme(usuario, null);
		
		
		
	}
}





