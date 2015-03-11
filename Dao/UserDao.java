package Dao;


import Entities.UserEntity;
import Util.BTCUtil;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserDao extends GenericCrudDAO{

    public UserDao(){
        super(UserEntity.class);
    }

    public int signUp(UserEntity user){
            //if user already exist return -1
            //else
            String btcAdress = BTCUtil.generateBTCAdress();
            user.getBtcWallet().;
            em.persist(user);
        return 0;
    }

    public UserEntity login(String userName, String password){
            //if userName and password matches
            //return UserEntity
            //else return null
        return null;
    }

}