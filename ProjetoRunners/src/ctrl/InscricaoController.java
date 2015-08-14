package ctrl;

import java.util.List;

import model.Inscricao;
import dao.InscricaoDao;


public class InscricaoController {

	
	public InscricaoController(){
	}

	private InscricaoDao inscricaoDao = new InscricaoDao();

	public void salvar(Inscricao inscricao)  {
		
		inscricaoDao.salvar(inscricao);
	}

	public void atualizar(Inscricao inscricao) {
		
		inscricaoDao.atualizar(inscricao);
	}

	public void remover(Inscricao inscricao) throws Exception {
			inscricaoDao.remover(inscricao);
	}

	public List<Inscricao> listar() {
		return inscricaoDao.listar();
	}

}
