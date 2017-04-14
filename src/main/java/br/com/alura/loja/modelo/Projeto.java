package br.com.alura.loja.modelo;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

public class Projeto {

	private String nome;
	private Long id;
	private int anoDeInicio;
	
	
	public Projeto (Long id, String nome, int ano) {
		this.nome = nome;
		this.id = id;
		this.anoDeInicio = ano;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getAnoDeInicio() {
		return anoDeInicio;
	}
	public void setAnoDeInicio(int anoDeInicio) {
		this.anoDeInicio = anoDeInicio;
	}

	public String toXml() {
		XStream xStream = new XStream();
		return xStream.toXML(this);
	}
	
	
	public String toJson() {
		return  new Gson().toJson(this);
	}
	 
	
}
