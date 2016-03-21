/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.web.controller;

import it.cnr.ilc.lc.hibernatesearchtest.Annotation;
import it.cnr.ilc.lc.omega.web.manager.ResourceManager;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Level;

/**
 *
 * @author angelo
 */
@Named
@SessionScoped
public class SearchController extends BaseController implements Serializable {

    List<Annotation> annotations;

    @Inject
    ResourceManager manager;

    public String search(String s) {
        log(Level.INFO, "u", "search: " + s);
        annotations = manager.getAnnotation(s);
        return "";
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> annotations) {
        this.annotations = annotations;
    }

}
