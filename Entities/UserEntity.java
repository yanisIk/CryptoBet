package Entities;


import Entities.BtcWalletEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="UserEntity") //User is a sql-99 reserved word
public class UserEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long id;
    private String username;
    private String password;
    @OneToOne(mappedBy="User")
    private BtcWalletEntity btcWallet;
    @Column(unique=true)
    private String email;
    @Column(name="is_admin")
    private boolean isAdmin;
    @Column(name="btc_payment_adress")
    private String btcPaymentAdress;

    public long getId(){
        return id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BtcWalletEntity getBtcWallet() {
        return btcWallet;
    }

    public void setBtcWallet(BtcWalletEntity btcWallet) {
        this.btcWallet = btcWallet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getBtcPaymentAdress() {
        return btcPaymentAdress;
    }

    public void setBtcPaymentAdress(String btcPaymentAdress) {
        this.btcPaymentAdress = btcPaymentAdress;
    }
        
    
}