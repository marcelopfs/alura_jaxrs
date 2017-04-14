package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Projeto;


@Path("projetos")
public class ProjetoResource {

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca (@PathParam("id") long id) {
		Projeto proj = new ProjetoDAO().busca(id);
		return proj.toXml();
	}
	

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String buscaJson (@PathParam("id") long id) {
		Projeto proj = new ProjetoDAO().busca(id);
		return proj.toJson();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_XML)
	public String adiciona(String conteudo) {
		Projeto p = (Projeto) new XStream().fromXML(conteudo);
		new ProjetoDAO().adiciona(p);
		
		return "<status>OK</status>";
	}
	
}