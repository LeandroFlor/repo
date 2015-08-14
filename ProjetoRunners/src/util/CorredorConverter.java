package util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.Corredor;
import ctrl.CorredorController;


@FacesConverter(value="corredorConverter")
public class CorredorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			System.out.println(value);
			Integer codigo = Integer.valueOf(value);
			try{
				CorredorController cCtrl = new CorredorController();
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
			Corredor corredor = (Corredor) value;
			return corredor.getId().toString();
		}
		return "";
	}
}