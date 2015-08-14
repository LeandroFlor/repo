package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Corredor;
import ctrl.CorredorController;


@ManagedBean(name="corredorBean")
@RequestScoped
public class CorredorBean {
	
	private Corredor corredor = new Corredor();
	private List<Corredor> listaCorredores;

	public Corredor getCorredor() {
		return corredor;
	}

	public void setCorredor(Corredor corredor) {
		this.corredor = corredor;
	}
	
	
	public List<Corredor> getListaCorredores() {
		if(listaCorredores == null){
			CorredorController corredorCtrl = new CorredorController();
			listaCorredores = corredorCtrl.listar();
		}
		return listaCorredores;
	}

	public void setListaCorredores(List<Corredor> listaCorredores) {
		this.listaCorredores = listaCorredores;
	}

	public String salvar(){
		CorredorController corredorCtrl = new CorredorController();
		corredorCtrl.salvar(corredor);
		novo();
		return "";
	}
	
	public void novo(){
		corredor = new Corredor();
		listaCorredores = null;
		
	}
	
	public void buscar(){
		
	}

}
