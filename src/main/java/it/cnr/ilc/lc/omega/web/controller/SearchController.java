/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.web.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.apache.log4j.Level;

/**
 *
 * @author angelo
 */
@Named
@SessionScoped
public class SearchController extends BaseController implements Serializable {

    public String search(String s) {
        log(Level.INFO, "u", "search: " + s);
        return "welcomePrimefaces";
    }

}
