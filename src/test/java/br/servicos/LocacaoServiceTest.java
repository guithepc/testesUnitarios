package br.servicos;


import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.utils.DataUtils;
import br.entidades.Filme;
import br.entidades.Locacao;
import br.entidades.Usuario;

public class LocacaoServiceTest {
	
	@Test
	public void testeJUnit() {
		//cen�rio
		
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Nigui");
		Filme filme = new Filme("Indiana Jones", 10, 5.0);
		
		//a��o
		
		Locacao locacao = service.alugarFilme(usuario, filme);
		
		
		//verifica��o
		
		Assert.assertTrue(locacao.getValor() == 5.0);
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
	}
}
