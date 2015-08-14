package util;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import model.Corridas;
import ctrl.CorridaController;


@WebServlet(value = "/corridas/*")
public class CorridaServlet extends HttpServlet {
	
	private static JAXBContext context;
	static {
		try {
			context = JAXBContext.newInstance(Corridas.class);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
	


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException {
		
		/*Corrida objetoAEscrever = (Corrida)localizaObjetoASerEnviado(req);
		System.out.println("obj; "+ objetoAEscrever.getDescricao());
		if (objetoAEscrever == null) {
			resp.sendError(404); //objeto não encontrado
			return ;
		}*/
		
		try {
			Marshaller marshaller = context.createMarshaller();
			resp.setContentType("application/xml;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			Corridas corridas = new Corridas();
			CorridaController cc = new CorridaController();
			corridas.setCorridas(cc.listarAbertas());
			marshaller.marshal(corridas, out);
		} catch (Exception e) {
			System.out.println("erro:"+e.getMessage());
			//resp.sendError(500, e.getMessage());
		}		
	}
	
	
	private String obtemIdentificador(HttpServletRequest req)
			throws Exception {
		String requestUri = req.getRequestURI();
		String[] pedacosDaUri = requestUri.split("/");
		boolean contextoCorridasEncontrado = false;
		for (String contexto : pedacosDaUri) {
			if (contexto.equals("corridas")) {
				contextoCorridasEncontrado = true;
				continue;
			}
			if (contextoCorridasEncontrado) {
				try {
					System.out.println("try "+URLDecoder.decode(contexto, "UTF-8"));
					return URLDecoder.decode(contexto, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					System.out.println(URLDecoder.decode(contexto));
					return URLDecoder.decode(contexto);
				}
			}
		}
		return "";
	}
	
	
	@SuppressWarnings("unused")
	private Object localizaObjetoASerEnviado(HttpServletRequest req) {
		Object objeto = null;
		CorridaController corridaC = new CorridaController();
		try {
			System.out.println("no localizar");
			String identificador = obtemIdentificador(req);
			if(!identificador.equals("")){
				objeto = corridaC.carregar(Integer.parseInt(identificador));
			}
		} catch (Exception e) {
			Corridas corridas = new Corridas();
			corridas.setCorridas(new ArrayList<>(corridaC.listar()));
			objeto = corridas;
		}
		return objeto;
	}
}