package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Corrida;
import ctrl.CorridaController;


@ManagedBean(name="corridaBean")
@RequestScoped
public class CorridaBean {
	
	private Corrida corrida = new Corrida();
	private List<Corrida> listaCorridas;

	public Corrida getCorrida() {
		return corrida;
	}

	public void setCorrida(Corrida corrida) {
		this.corrida = corrida;
	}
	
	
	public List<Corrida> getListaCorridas() {
		if(listaCorridas == null){
			CorridaController corridaCtrl = new CorridaController();
			listaCorridas = corridaCtrl.listar();
		}
		return listaCorridas;
	}

	public void setListaCorridas(List<Corrida> listaCorridas) {
		this.listaCorridas = listaCorridas;
	}

	public String salvar(){
		CorridaController corridaCtrl = new CorridaController();
		corridaCtrl.salvar(corrida);
		novo();
		return "";
	}
	
	public void novo(){
		corrida = new Corrida();
		listaCorridas = null;
		
	}
	
	public void buscar(){
		
	}
	
	
	

}
