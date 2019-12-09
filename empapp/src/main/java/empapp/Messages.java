package empapp;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;

@ApplicationScoped
public class Messages {

    public void addFlashMessage(String message) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("successMessage", message);
    }
}
