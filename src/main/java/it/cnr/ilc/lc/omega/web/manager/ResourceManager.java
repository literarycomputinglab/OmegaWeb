/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.web.manager;

import com.sun.javafx.scene.text.HitInfo;
import it.cnr.ilc.lc.hibernatesearchtest.Annotation;
import it.cnr.ilc.lc.hibernatesearchtest.App;
import it.cnr.ilc.lc.hibernatesearchtest.Source;
import it.cnr.ilc.lc.omega.web.domain.KwicHit;
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
    public List<KwicHit> getSourceKWC(String keyWord) {
        String key = keyWord;
        List<Source> sources = App.omegaKeySearch(key);
        return hitsPopulate(sources, key);
    }
//
//    public List<Annotation> getWordFragments() {
//        App.omegafragments();
//    }

    private List<KwicHit> hitsPopulate(List<Source> sources, String key) {
        List<KwicHit> hits = new ArrayList<>();
        for (Source source : sources) {
            String[] parts = source.getContent().getData().split("\\s" + key + "\\s");
            for (int i = 0; i < parts.length - 1; i++) {
                KwicHit kwic = new KwicHit();
                kwic.setMatchWord(key);
                kwic.setContextLeft(parts[i]);
                kwic.setContextRight(parts[i + 1]);
                hits.add(kwic);
            }

        }

        return hits;
    }

    public static void main(String[] args) {
        ResourceManager manager = new ResourceManager();
        List<KwicHit> hits = null;
        hits = manager.getSourceKWC("per");
        for (KwicHit hit : hits) {
            System.err.println(hit.toString());
        }

    }
}
