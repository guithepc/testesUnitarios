package br.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;

import br.entidades.Filme;
import br.entidades.Locacao;
import br.entidades.Usuario;
import br.exceptions.FilmeSemEsqtoqueException;
import br.exceptions.LocadoraException;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, Filme filme) throws FilmeSemEsqtoqueException, LocadoraException{
		
		
		if (usuario == null) {
			throw new LocadoraException("Usu·rio vazio");
		}
		
		if (filme == null) {
			throw new LocadoraException("Filme vazio");
		}
		
		if (filme.getEstoque() == 0) {
			throw new FilmeSemEsqtoqueException("Filme sem estoque");
		}
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar m√©todo para salvar
		
		return locacao;
	}

	
}



