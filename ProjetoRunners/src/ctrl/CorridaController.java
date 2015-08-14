package ctrl;

import java.util.List;

import model.Corrida;
import dao.CorridaDao;


public class CorridaController {

	
	public CorridaController(){
	}

	private CorridaDao corridaDao = new CorridaDao();

	public void salvar(Corrida corrida)  {
		Integer codigo = corrida.getId();
		if(codigo == null){
			corridaDao.salvar(corrida);
		}else{
			System.out.println("atualizei");
			corridaDao.atualizar(corrida);
		}
	}

	public void atualizar(Corrida corrida) {
		
		corridaDao.atualizar(corrida);
	}

	public void remover(Corrida corrida) throws Exception {
			corridaDao.remover(corrida);
	}

	public List<Corrida> listar() {
		return corridaDao.listar();
	}
	
	public List<Corrida> listarAbertas() {
		return corridaDao.listarAbertas();
	}
	
	public Corrida carregar(Integer id) {
		Corrida corrida = new Corrida();
		corrida = corridaDao.carregar(id);
		return corrida;
	}

}
