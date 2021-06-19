package br.servicos;


import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import br.ce.wcaquino.utils.DataUtils;
import br.entidades.Filme;
import br.entidades.Locacao;
import br.entidades.Usuario;

public class LocacaoServiceTest {
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Test
	public void testeJUnit() {
		//cenário
		
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Nigui");
		Filme filme = new Filme("Indiana Jones", 10, 5.0);
		
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
}
