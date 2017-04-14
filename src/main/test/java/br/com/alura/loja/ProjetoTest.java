package br.com.alura.loja;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Projeto;

public class ProjetoTest {


	private HttpServer server;
	
	public void startaServidor() {
		server = Servidor.inicializaServidor();
	}

	@Before
	public void before() {
		startaServidor();
	}
	
	@Test
    public void testaProjeto() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        String conteudo = target.path("/projetos/1").request().get(String.class);
        
        System.out.println(conteudo);
        
        Gson gson = new Gson();
        
        Projeto fromJson = gson.fromJson(conteudo, Projeto.class);
        
        System.out.println("Nome >>>>>>>>>> " + fromJson.getNome());
        
         
//        Projeto p = (Projeto) new XStream().fromJs(conteudo);
//        
//        System.out.println(p);
//        assertEquals("Minha loja", p.getNome());
    }
	
	@After
	public void fechaServidor() {
		server.stop();
	}
	
		
	
}
