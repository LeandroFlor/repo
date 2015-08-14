package dao;

import java.util.ArrayList;
import java.util.List;

import model.Corrida;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

public class CorridaDao {


	
	
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	 
	private Session sessao;
	 
	

	public Session getSessao() {
		return sessao;
	}


	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}


	public void salvar(Corrida corrida) {
	 		
			try {
				this.sessao = sessionFactory.openSession();
				this.sessao.beginTransaction();
				this.sessao.save(corrida);
			
				this.sessao.getTransaction().commit();
				}catch(Exception e){
					this.sessao.getTransaction().rollback();
					
					System.out.println("Erro ao salvar... "+e.getMessage());
				}finally{
					this.sessao.close();
				}
		}



	public void atualizar(Corrida corrida){
	
		try {
			this.sessao = sessionFactory.openSession();
			this.sessao.beginTransaction();
			this.sessao.update(corrida);
			this.sessao.getTransaction().commit();
			}catch(Exception e){
				this.sessao.getTransaction().rollback();
				System.out.println("Erro ao atualizar... "+e.getMessage());
			}finally{
				this.sessao.close();
			}
	}
	
	public void remover(Corrida corrida){
	
		try {
			this.sessao = sessionFactory.openSession();
			this.sessao.beginTransaction();
			this.sessao.delete(corrida);
			this.sessao.getTransaction().commit();
			}catch(Exception e){
				this.sessao.getTransaction().rollback();
				System.out.println("Erro ao deletar... "+e.getMessage());
			}finally{
				this.sessao.close();
			}
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Corrida> listar(){
		List<Corrida> listaCorridas = new ArrayList<Corrida>();		
		try {
			
			this.sessao = sessionFactory.openSession();
			this.sessao.beginTransaction();
			listaCorridas = (ArrayList<Corrida>)this.sessao.createCriteria(Corrida.class).list();
			this.sessao.getTransaction().commit();

		} catch (Exception e) {
			this.sessao.getTransaction().rollback();
			System.err.println(String.format("ocorreu um erro ao listar os agentes cadastradas. Erro: %s", e.getMessage()));
		} finally {
			this.sessao.close();
		}
		
		return listaCorridas;
	}

	
	@SuppressWarnings("unchecked")
	public List<Corrida> listarAbertas(){
		List<Corrida> listaCorridas = new ArrayList<Corrida>();		
		try {
			
			this.sessao = sessionFactory.openSession();
			this.sessao.beginTransaction();
			listaCorridas = (ArrayList<Corrida>)this.sessao.createCriteria(Corrida.class)
					.add(Restrictions.eq("status", "Agendada")).list();
			this.sessao.getTransaction().commit();

		} catch (Exception e) {
			this.sessao.getTransaction().rollback();
			System.err.println(String.format("ocorreu um erro ao listar os agentes cadastradas. Erro: %s", e.getMessage()));
		} finally {
			this.sessao.close();
		}
		
		return listaCorridas;
	}
	
	
public Corrida carregar(Integer id){
	
		
		Corrida corridaLocalizada = new Corrida();
		try{
			this.sessao = sessionFactory.openSession();
			this.sessao.beginTransaction();
			corridaLocalizada = (Corrida)this.sessao.get(Corrida.class, id);
			this.sessao.getTransaction().commit();
		}catch(Exception e){
			this.sessao.getTransaction().rollback();
			System.out.println("erro.." + e.getMessage());
		}finally{
			this.sessao.close();
		}
		return corridaLocalizada;

	}
	

	
	
	
	

}
