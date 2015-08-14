package util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.Corrida;
import ctrl.CorridaController;


@FacesConverter(value="corridaConverter")
public class CorridaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			System.out.println(value);
			Integer codigo = Integer.valueOf(value);
			try{
				CorridaController cCtrl = new CorridaController();
				return cCtrl.carregar(codigo);
				//return rn.pesquisaPorNome(value);
			}catch(Exception e){
				
			}
		}
			
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Corrida corrida = (Corrida) value;
			return corrida.getId().toString();
		}
		return "";
	}
}