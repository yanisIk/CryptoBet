import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
 
@ManagedBean
@Stateful
@SessionScoped

public class LoginSignUpBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private String message, uname, password, email;
 
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getUname() {
        return uname;
    }
 
    public void setUname(String uname) {
        this.uname = uname;
    }
 
    public String signUp(){
        UserEntity user = new UserEntity();
        user.setUserName(uname);
        user.setPassword(password);
        user.setEmail(email);

        int signup = userDao.signUp(user);
        if(signup != -1){
            return "login";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "User already exists"));
            return "signup"
        }
    }

    public String login() {
        UserEntity user = UserDAO.login(uname, password);
        if (user != null) {
            // get Http Session and store username
            HttpSession session = FaceUtil.getSession();
            session.setAttribute("user", user);
 
            return "home";
        } else {
 
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Invalid Login!",
                    "Please Try Again!"));
 
            // invalidate session, and redirect to other pages
            logout();
            //message = "Invalid Login. Please Try Again!";
            //return "login";
        }
    }
 
    public String logout() {
      HttpSession session = Util.getSession();
      session.invalidate();
      return "login";
   }


}

