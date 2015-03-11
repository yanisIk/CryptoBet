package Dao;


import Entities.BtcWalletEntity;
import Entities.TransactionEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class WalletDao{
	
	@PersistenceContext
	private EntityManager em;


	public BtcWalletEntity getBtcWallet(long walletID){
		TypedQuery<BtcWalletEntity> query = em.createNamedQuery("BtcWalletEntity.findWallet", BtcWalletEntity.class);
		query.setParameter("walletID",walletID);
		return query.getSingleResult();
	}
}