package util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import model.Corrida;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LeitorXML {
	
	public List<Corrida> carregar(InputStream content){
		List<Corrida> listaCorridas = new ArrayList<Corrida>();
	
	try {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(content);
		doc.getDocumentElement().normalize();
		
		NodeList nList = doc.getElementsByTagName("corrida");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
					
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
					
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				
				Corrida corrida = new Corrida();
				corrida.setId(Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent()));
				corrida.setDescricao(eElement.getElementsByTagName("descricao").item(0).getTextContent());
				corrida.setCidade(eElement.getElementsByTagName("cidade").item(0).getTextContent());
				corrida.setEstado(eElement.getElementsByTagName("estado").item(0).getTextContent());
				corrida.setNome(eElement.getElementsByTagName("nome").item(0).getTextContent());
				corrida.setStatus(eElement.getElementsByTagName("status").item(0).getTextContent());
				corrida.setValor(eElement.getElementsByTagName("valor").item(0).getTextContent());
				corrida.setData(eElement.getElementsByTagName("data").item(0).getTextContent());
				
				listaCorridas.add(corrida);
			}
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	return listaCorridas;
	}
}
