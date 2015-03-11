package Dao;

import Entities.TransactionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class TransactionDao extends GenericCrudDAO{
	
	public TransactionDao(){
            super(TransactionEntity.class);
        }
	
	public void createTransaction(TransactionEntity tr){
            em.persist(tr);
	}
	public void updateTransaction(TransactionEntity tr){
             em.persist(tr);
	}
	public void deleteTransaction(TransactionEntity tr){
		em.remove(tr);
	}
	public TransactionEntity getTransaction(Long id){
		return em.createNamedQuery("TransactionEntity.findAll", TransactionEntity.class).setParameter("id",id).getSingleResult();
	}
	public List<TransactionEntity> getAllBTCTransactions(){
		return em.createNamedQuery("TransactionEntity.findAllBTC", TransactionEntity.class).getResultList();
	}
	
	public List<TransactionEntity> getBetRewards(long betID){
		return em.createNamedQuery("TransactionEntity.findRewards", TransactionEntity.class).setParameter("betID",betID).getResultList();
	}
	public List<TransactionEntity> getTransactionsByBet(long betID){
		TypedQuery<TransactionEntity> query = em.createNamedQuery("TransactionEntity.findByBet", TransactionEntity.class);
		query.setParameter("betID",betID);
		return query.getResultList();
	}
	public List<TransactionEntity> getTransactionsByWallet(long walletID){
		TypedQuery<TransactionEntity> query = em.createNamedQuery("TransactionEntity.findByWallet", TransactionEntity.class);
		query.setParameter("walletID",walletID);
		return query.getResultList();
	}
	public List<TransactionEntity> getTransactionsByWalletBet(long walletID, long betID){
		TypedQuery<TransactionEntity> query = em.createNamedQuery("TransactionEntity.findByWalletBet", TransactionEntity.class);
		query.setParameter("walletID",walletID);
		query.setParameter("betID",betID);
		return query.getResultList();
	}
	public List<TransactionEntity> getTransactionsByWalletBetTeam(long walletID, long betID, String teamName){
		TypedQuery<TransactionEntity> query = em.createNamedQuery("TransactionEntity.findWalletBetTeam", TransactionEntity.class);
		query.setParameter("walletID",walletID);
		query.setParameter("betID",betID);
		query.setParameter("teamName", teamName);
		return query.getResultList();
	}
	
	
}