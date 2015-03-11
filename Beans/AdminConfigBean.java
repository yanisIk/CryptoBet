package Beans;


import Dao.BetDao;
import Entities.BetEntity;
import Entities.UserEntity;
import Util.FaceUtil;
import java.util.List;
import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

@Stateful
@ManagedBean
public class AdminConfigBean {

	private List<BetEntity> betRequestList_;
        private UserEntity UserEntity_;
        private BetEntity fullBetToCreate_;
	private int delay_;
        @Inject
        private BetDao betDao;
	
	
	
	public List<BetEntity> getBetRequestList(){
		return betDao.getNonApprovedBets();
	}
	
	public void createFullBet(){
		fullBetToCreate_.setIsApproved(true);
		betDao.save(fullBetToCreate_);
	}
	
	public UserEntity getUserEntity(){
		return FaceUtil.getUser();
	}
	
}