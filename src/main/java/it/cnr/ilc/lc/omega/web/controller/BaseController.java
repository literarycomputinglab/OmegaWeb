/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.web.controller;

//import it.cnr.ilc.traduco.domain.Labeled;
import java.io.Serializable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author angelo
 * @author davide
 */
public abstract class BaseController implements Serializable {

    public void log(Level level, String username, String message) {
        message = "(" + (username == null ? "null" : username) + ") " + message;
        //Logger.getLogger("traduco").log(level, message);
        System.err.println(message);
    }

    public void log(Level level, String username, String message, Throwable t) {
        message = "(" + (username == null ? "null" : username) + ") " + message;
        //Logger.getLogger("traduco").log(level, message, t);
        System.err.println(message);
    }

    public void error(String summary, String... details) {
        String detail = buildDetail(details);
        message(FacesMessage.SEVERITY_ERROR, summary, detail);
    }

    public void warn(String summary, String... details) {
        summary = getLabel(summary);
        String detail = buildDetail(details);
        message(FacesMessage.SEVERITY_WARN, summary, detail);
    }

    public void info(String summary, String... details) {
        summary = getLabel(summary);
        String detail = buildDetail(details);
        message(FacesMessage.SEVERITY_INFO, summary, detail);
    }

    protected String buildDetail(String[] details) {
        String detail = getLabel(details[0]);
        for (int i = 1; i < details.length; i++) {
            detail = detail.replaceAll("\\{" + (i - 1) + "\\}", details[i]);
        }
        return detail;
    }

    public void message(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public String getLabel(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle resourceBundle = context.getApplication().getResourceBundle(context, "label");
        try {
            return resourceBundle.getString(key);
        } catch (MissingResourceException ex) {
            return "???" + key + "???";
        }
    }

//    public String getLabel(Labeled labeled) {
//        return Labeled.getLabel(labeled);
//    }
    public Locale getRequestLocale() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

}
