package ctrl;

import java.util.List;

import model.Corredor;
import dao.CorredorDao;


public class CorredorController {

	
	public CorredorController(){
	}

	private CorredorDao corredorDao = new CorredorDao();

	public void salvar(Corredor corredor)  {
		Integer codigo = corredor.getId();
		if(codigo == null){
			corredorDao.salvar(corredor);
		}else{
			corredorDao.atualizar(corredor);
		}
	}

	public void atualizar(Corredor corredor) {
		
		corredorDao.atualizar(corredor);
	}

	public void remover(Corredor corredor) throws Exception {
			corredorDao.remover(corredor);
	}

	public List<Corredor> listar() {
		return corredorDao.listar();
	}

	public Corredor carregar(Integer id) {
		return corredorDao.carregar(id);
	}
}
