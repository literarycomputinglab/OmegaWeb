/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.web.controller;

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.log4j.Level;

/**
 *
 * @author angelo
 */
@Named
@SessionScoped
public class HomeController
        extends BaseController implements Serializable {

    private static final String USER = "user";

    public String getAccountName() {
        return USER;
    }

    public void exitAction() throws IOException {
        log(Level.INFO, USER, "sign out");

        FacesContext.getCurrentInstance().getExternalContext().redirect("/omegaWeb/");
    }

    public String homeAction() {
        log(Level.INFO, USER, "navigate to home");
        return "homeView";
    }

    public String accountAction() {
        log(Level.INFO, USER, "navigate to accounts");
        return "homeView";
    }

    public String glossaryAction() {
        log(Level.INFO, USER, "navigate to glossaries");
        return "homeView";
    }

    public String translationAction() {
        log(Level.INFO, USER, "navigate to translations");
        return "";
    }

    public boolean isEnableAccountMenu() {
        return true;
    }

    public boolean isEnableGlossaryMenu() {
        return true;
    }

    public boolean isEnableTranslationMenu() {
        return true;
    }
}
