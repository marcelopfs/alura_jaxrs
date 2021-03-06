package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;

@Path("carrinhos")
public class CarrinhoResource {
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca (@PathParam("id") long id) {
		Carrinho busca = new CarrinhoDAO().busca(id);
		return busca.toXml();
	}
	
//	@Path("{id}")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public String buscaJson (@PathParam("id") long id) {
//		Carrinho busca = new CarrinhoDAO().busca(id);
//		return busca.toJson();
//	}
	
	@POST
	@Produces(MediaType.APPLICATION_XML)
	public String adiciona(String conteudo) {
		Carrinho c = (Carrinho) new XStream().fromXML(conteudo);
		new CarrinhoDAO().adiciona(c);
		return "<status>sucesso</status>";	
	}
	
}
