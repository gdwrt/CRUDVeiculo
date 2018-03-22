package br.com.geovanidias.util.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {

	public static void addSuccessMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}

	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}

	public static void addWarningMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, message, message));
	}

	public static void redirecionarObjetoFlash(String key, Object value) { 
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put(key, value);
	}

	public static Object recuperarObjetoFlash(Object key) {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(key);
	}
}
