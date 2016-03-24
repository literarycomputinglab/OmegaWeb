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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        Pattern p = Pattern.compile("\\b(" + key + ")\\b", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
        List<KwicHit> hits = new ArrayList<>();
        for (Source source : sources) {
            String content = source.getContent().getData();
            Matcher m = p.matcher(content);

            while (m.find()) {
                KwicHit kwic = new KwicHit();
                String sx = content.substring((m.start() < 40) ? 0 : m.start() - 40, m.start());
                String w = m.group(1);
                String dx = content.substring(m.end(), (m.end() + 40 > content.length()) ? content.length() : m.end() + 40);

                kwic.setMatchWord(w);
                kwic.setContextLeft(sx);
                kwic.setContextRight(dx);
                hits.add(kwic);

            }
        }

        return hits;
    }

    public static void main(String[] args) {
        ResourceManager manager = new ResourceManager();
        List<KwicHit> hits = null;
        hits = manager.getSourceKWC("E");
        for (KwicHit hit : hits) {
            System.err.println(hit.toString());
        }

    }
}
