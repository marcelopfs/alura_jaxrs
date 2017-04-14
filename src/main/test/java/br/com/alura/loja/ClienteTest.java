package br.com.alura.loja;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import junit.framework.Assert;

public class ClienteTest {

	private HttpServer server;
	
	public void startaServidor() {
		server = Servidor.inicializaServidor();
	}

	@Before
	public void before() {
		startaServidor();
	}
	
	@Test
    public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperadoXML() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        String conteudo = target.path("/carrinhos/1").request().get(String.class);
        
        Carrinho c = (Carrinho) new XStream().fromXML(conteudo);
        System.out.println(c.getRua());
        assertEquals("Rua Vergueiro 3185, 8 andar", c.getRua());
    }
	
	@Test
    public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperadoJSON() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        String conteudo = target.path("/carrinhos/1").request().get(String.class);

        Carrinho c1 = new Gson().fromJson(conteudo, Carrinho.class);
        System.out.println(conteudo);
        System.out.println(c1.getRua());
        assertEquals("Rua Vergueiro 3185, 8 andar", c1.getRua());        
    }
	
	@Test
    public void testaQueBuscarUmCarrinhoPostaCorretamente() {
		Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");

        Carrinho carrinho = new Carrinho();
        carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
        carrinho.setRua("Rua Vergueiro");
        carrinho.setCidade("Sao Paulo");
        String xml = carrinho.toXml(carrinho);
        
        Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);

        Response response = target.path("/carrinhos").request().post(entity);
        Assert.assertEquals("<status>sucesso</status>", response.readEntity(String.class));
    }
	
	
	@After
	public void fechaServidor() {
		server.stop();
	}
	
	
}
