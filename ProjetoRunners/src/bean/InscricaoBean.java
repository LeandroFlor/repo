package bean;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Corredor;
import model.Corrida;
import model.Inscricao;
import util.LeitorXML;
import ctrl.CorredorController;
import ctrl.InscricaoController;


@ManagedBean(name="inscricaoBean")
@RequestScoped
public class InscricaoBean {
	
	private Inscricao inscricao = new Inscricao();
	private Corredor corredor = new Corredor();
	private Corrida corrida = new Corrida();
	private List<Corrida> listaCorridas;
	private List<Inscricao> listaInscricoes;
	
	
	private void novo(){
		inscricao = new Inscricao();
		corredor = new Corredor();
		listaInscricoes = null;
	}

	public Inscricao getInscricao() {
		return inscricao;
	}

	public void setInscricao(Inscricao inscricao) {
		this.inscricao = inscricao;
	}
	
	
	public List<Inscricao> getListaInscricoes() {
		if(listaInscricoes == null){
			InscricaoController inscricaoCtrl = new InscricaoController();
			listaInscricoes = inscricaoCtrl.listar();
		}
		return listaInscricoes;
	}

	public void setListaInscricoes(List<Inscricao> listaInscricoes) {
		this.listaInscricoes = listaInscricoes;
	}


	public Corredor getCorredor() {
		return corredor;
	}

	public void setCorredor(Corredor corredor) {
		this.corredor = corredor;
	}

	public List<Corrida> getListaCorridas() throws IOException {
		if(listaCorridas == null){
			URL url = new URL("http://localhost:8080/ProjetoRunners/corridas");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			InputStream content = connection.getInputStream();
			listaCorridas = new LeitorXML().carregar(content);
		}
		return listaCorridas;
	}

	public void setListaCorridas(List<Corrida> listaCorridas) {
		this.listaCorridas = listaCorridas;
	}

	
	
	
	public Corrida getCorrida() {
		return corrida;
	}

	public void setCorrida(Corrida corrida) {
		this.corrida = corrida;
	}

	public String salvar(){
		inscricao.setCorredor(corredor);
		InscricaoController inscricaoCtrl = new InscricaoController();
		inscricaoCtrl.salvar(inscricao);
		novo();
		return "";
	}
	
	public void excluir(){
		
	}
	
	public void buscar(){
		
	}
	
	public String cadastrarCorredor(){
		CorredorController corredorCtrl = new CorredorController();
		corredorCtrl.salvar(corredor);
		return "";
	}
	
	
	
	

}
