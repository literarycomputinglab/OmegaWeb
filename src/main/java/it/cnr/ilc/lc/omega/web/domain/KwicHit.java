/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.web.domain;

/**
 *
 * @author angelo
 */
public class KwicHit {

    private String contextLeft;
    private String matchWord;
    private String contextRight;

    public String getContextLeft() {
        return contextLeft;
    }

    public void setContextLeft(String contextLeft) {
        this.contextLeft = contextLeft;
    }

    public String getContextRight() {
        return contextRight;
    }

    public void setContextRight(String contextRight) {
        this.contextRight = contextRight;
    }

    public String getMatchWord() {
        return matchWord;
    }

    public void setMatchWord(String matchWord) {
        this.matchWord = matchWord;
    }

    public String getContextLeftWindow() {

        int n = 40 - getContextLeft().length();
        StringBuilder padding = new StringBuilder();
        for (int i = 0; i < n; i++) {
            padding.append("_");
        }
        String ret = padding.append(getContextLeft()).toString();
        return ret.replace(' ', '_'); 
    }

    public String getContextRightWindow() {
        int n = 40 - getContextRight().length();
        StringBuilder padding = new StringBuilder();
        for (int i = 0; i < n; i++) {
            padding.append('_');
        }
        String ret = getContextRight().concat(padding.toString());
        return ret.replace(' ', '_');
    }

    @Override
    public String toString() {

        return String.format("[%s] <%s> [%s]", getContextLeft(), getMatchWord(), getContextRight());

    }

}
