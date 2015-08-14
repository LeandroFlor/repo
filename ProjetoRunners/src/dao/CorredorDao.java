package dao;

import java.util.ArrayList;
import java.util.List;

import model.Corredor;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import util.HibernateUtil;

public class CorredorDao {


	
	
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	 
	private Session sessao;
	 
	

	public Session getSessao() {
		return sessao;
	}


	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}


	public void salvar(Corredor corredor) {
	 		
			try {
				this.sessao = sessionFactory.openSession();
				this.sessao.beginTransaction();
				this.sessao.save(corredor);
			
				this.sessao.getTransaction().commit();
				}catch(Exception e){
					this.sessao.getTransaction().rollback();
					
					System.out.println("Erro ao salvar... "+e.getMessage());
				}finally{
					this.sessao.close();
				}
		}



	public void atualizar(Corredor corredor){
	
		try {
			this.sessao = sessionFactory.openSession();
			this.sessao.beginTransaction();
			this.sessao.update(corredor);
			this.sessao.getTransaction().commit();
			}catch(Exception e){
				this.sessao.getTransaction().rollback();
				System.out.println("Erro ao atualizar... "+e.getMessage());
			}finally{
				this.sessao.close();
			}
	}
	
	public void remover(Corredor corredor){
	
		try {
			this.sessao = sessionFactory.openSession();
			this.sessao.beginTransaction();
			this.sessao.delete(corredor);
			this.sessao.getTransaction().commit();
			}catch(Exception e){
				this.sessao.getTransaction().rollback();
				System.out.println("Erro ao deletar... "+e.getMessage());
			}finally{
				this.sessao.close();
			}
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Corredor> listar(){
		List<Corredor> listaCorredores = new ArrayList<Corredor>();		
		try {
			
			this.sessao = sessionFactory.openSession();
			this.sessao.beginTransaction();
			listaCorredores = (ArrayList<Corredor>)this.sessao.createCriteria(Corredor.class).list();
			this.sessao.getTransaction().commit();

		} catch (Exception e) {
			this.sessao.getTransaction().rollback();
			System.err.println(String.format("ocorreu um erro ao listar os agentes cadastradas. Erro: %s", e.getMessage()));
		} finally {
			this.sessao.close();
		}
		System.out.println("size: "+listaCorredores.size());
		return listaCorredores;
	}

	
public Corredor carregar(Integer id){
	
		
		Corredor corredorLocalizado = new Corredor();
		try{
			this.sessao = sessionFactory.openSession();
			this.sessao.beginTransaction();
			corredorLocalizado = (Corredor)this.sessao.get(Corredor.class, id);
			this.sessao.getTransaction().commit();
		}catch(Exception e){
			this.sessao.getTransaction().rollback();
			System.out.println("erro.." + e.getMessage());
		}finally{
			this.sessao.close();
		}
		return corredorLocalizado;

	}
	
	
	
	

}
