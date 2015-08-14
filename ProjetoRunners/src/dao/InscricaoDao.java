package dao;

import java.util.ArrayList;
import java.util.List;

import model.Inscricao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import util.HibernateUtil;

public class InscricaoDao {


	
	
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	 
	private Session sessao;
	 
	

	public Session getSessao() {
		return sessao;
	}


	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}


	public void salvar(Inscricao inscricao) {
	 		
			try {
				this.sessao = sessionFactory.openSession();
				this.sessao.beginTransaction();
				this.sessao.save(inscricao);
			
				this.sessao.getTransaction().commit();
				}catch(Exception e){
					this.sessao.getTransaction().rollback();
					
					System.out.println("Erro ao salvar... "+e.getMessage());
				}finally{
					this.sessao.close();
				}
		}



	public void atualizar(Inscricao inscricao){
	
		try {
			this.sessao = sessionFactory.openSession();
			this.sessao.beginTransaction();
			this.sessao.update(inscricao);
			this.sessao.getTransaction().commit();
			}catch(Exception e){
				this.sessao.getTransaction().rollback();
				System.out.println("Erro ao atualizar... "+e.getMessage());
			}finally{
				this.sessao.close();
			}
	}
	
	public void remover(Inscricao inscricao){
	
		try {
			this.sessao = sessionFactory.openSession();
			this.sessao.beginTransaction();
			this.sessao.delete(inscricao);
			this.sessao.getTransaction().commit();
			}catch(Exception e){
				this.sessao.getTransaction().rollback();
				System.out.println("Erro ao deletar... "+e.getMessage());
			}finally{
				this.sessao.close();
			}
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Inscricao> listar(){
		List<Inscricao> listaInscricoes = new ArrayList<Inscricao>();		
		try {
			
			this.sessao = sessionFactory.openSession();
			this.sessao.beginTransaction();
			listaInscricoes = (ArrayList<Inscricao>)this.sessao.createCriteria(Inscricao.class).list();
			this.sessao.getTransaction().commit();

		} catch (Exception e) {
			this.sessao.getTransaction().rollback();
			System.err.println(String.format("ocorreu um erro ao listar os agentes cadastradas. Erro: %s", e.getMessage()));
		} finally {
			this.sessao.close();
		}
		
		return listaInscricoes;
	}

	

	
	
	
	

}
