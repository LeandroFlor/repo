package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Corridas {
	
	private List<Corrida> corridas = new ArrayList<>();
	
	@XmlElement(name="corrida")
	public List<Corrida> getCorridas() {
		return corridas;
	}
	
	public void setCorridas(List<Corrida> corridas) {
		this.corridas = corridas;
	}

}
