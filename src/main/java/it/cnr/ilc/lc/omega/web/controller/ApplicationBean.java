/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.web.controller;

import it.cnr.ilc.lc.hibernatesearchtest.App;
import it.cnr.ilc.lc.omega.web.domain.ResourceType;
import it.cnr.ilc.lc.omega.web.manager.ResourceManager;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author angelo
 */
@Named
@ApplicationScoped
public class ApplicationBean {

    private static final String VERSION = "0.0.1";

    @Inject
    private ResourceManager resourceManager;

    private List<ResourceType> resourceTypes;

    @PostConstruct
    public void init() {
        resourceTypes = resourceManager.getResourceTypes();
    }

    public String  shutdown() {
        App.shutdown();
        return "";
    }

    public List<ResourceType> getResourceTypes() {
        return resourceTypes;
    }

    public String emptyMessage(String text, String emptyMessage) {
        return emptyMessage(text, text, emptyMessage);
    }

    public String emptyMessage(String test, String text, String emptyMessage) {
        return test == null || test.equals("") ? emptyMessage : text;
    }

    public String getNoteHtml(String color, String symbol) {
        return ""; //NoteManager.NOTE_HTML.replaceAll(":c", color).replaceAll(":s", symbol);
    }

    public String getGlossaryHtml(String color, String name) {
        return ""; //GlossaryManager.GLOSSARY_HTML.replaceAll(":c", color).replaceAll(":n", name);
    }

    public String getVersion() {
        return VERSION;
    }

    public String getHelpLink() {
        return "";//HELP_LINK;
    }

}
