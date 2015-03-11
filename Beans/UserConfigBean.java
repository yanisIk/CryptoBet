@Stateful
@ManagedBean
public class UserConfigBean{

	protected BetEntity betEntity_;
	protected UserEntity userEntity_;
	
	@Inject
	protected BetDao betDao_;
	@Inject
	protected UserDao userDao_;
	
	
	public BetEntity getBetEntity(){
		if(betEntity_ == null){
			betEntity_ = new BetEntity();
		}
		return betEntity_;
	}

	public UserEntity getUserEntity(){
		return FaceUtil.getUserEntity();
	}
	
	public BetEntity createBetRequest(){
		betEntity_.setIsApproved(false);
		betDao_.saveBet(betEntity_);
	}

	public setPaymentAdress(){
		userDao_.save(userEntity_);
	}

	public changePassword(){
		userDao_.save(userEntity_);
		FaceUtil.logout();
	}

}