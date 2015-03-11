package Util;

import Entities.UserEntity;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FaceUtil{

	public static HttpSession getSession() {
        return (HttpSession)
          FacesContext.
          getCurrentInstance().
          getExternalContext().
          getSession(false);
      }
       
      public static HttpServletRequest getRequest() {
       return (HttpServletRequest) FacesContext.
          getCurrentInstance().
          getExternalContext().getRequest();
      }

       
      public static UserEntity getUser()
      {
        HttpSession session = getSession();
        if ( session != null )
            return (UserEntity) session.getAttribute("user");
        else
            return null;
      }

}