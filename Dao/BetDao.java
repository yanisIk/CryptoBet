package Dao;


import Entities.BetEntity;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class BetDao extends GenericCrudDAO<BetEntity>{

        public BetDao(){
            super(BetEntity.class);
        }
    
        public BetEntity getBet(long id){
            return em.createNamedQuery("BetEntity.findById", BetEntity.class).setParameter("id", id).getSingleResult();
        }
        
	public List<BetEntity> getNonStartedBets(){
		return em.createNamedQuery("BetEntity.findNonStarted", BetEntity.class).getResultList();
	}
	public List<BetEntity> getInProgressBets(){
		return em.createNamedQuery("BetEntity.findInProgress", BetEntity.class).getResultList();
	}
	public List<BetEntity> getNonApprovedBets(){
		return em.createNamedQuery("BetEntity.findNonApproved", BetEntity.class).getResultList();
	}
	public List<BetEntity> getDota2Bets(){
		return em.createNamedQuery("BetEntity.findDota2", BetEntity.class).getResultList();
	}
	public List<BetEntity> getLolBets(){
		return em.createNamedQuery("BetEntity.findLol", BetEntity.class).getResultList();
	}
	public List<BetEntity> getCsgoBets(){
		return em.createNamedQuery("BetEntity.findCsgo", BetEntity.class).getResultList();
	}

    public List<BetEntity> getFinishedBets() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}