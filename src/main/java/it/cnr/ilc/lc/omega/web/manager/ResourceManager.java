/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.web.manager;

import it.cnr.ilc.lc.hibernatesearchtest.Annotation;
import it.cnr.ilc.lc.hibernatesearchtest.App;
import it.cnr.ilc.lc.hibernatesearchtest.Source;
import it.cnr.ilc.lc.omega.web.domain.ResourceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author angelo
 */
public class ResourceManager implements Serializable {

    public List<ResourceType> getResourceTypes() {
        return new ArrayList<>();
    }

    public List<Annotation> getAnnotation(String s) {
        return App.omegaFacetExample(s);
    }

//    public List<Annotation> getWorks() {
//        return App.omegaWorks();
//    }
//
    public List<Source> getSourceKWC(String keyWord) {
        return App.omegaKeySearch(keyWord);
    }
//
//    public List<Annotation> getWordFragments() {
//        App.omegafragments();
//    }

}
