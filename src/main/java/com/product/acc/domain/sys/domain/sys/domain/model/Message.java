package com.product.acc.domain.sys.domain.sys.domain.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Message Bean Class
 * @author inomata
 * @version $Revision$
 */
public class Message {

    /** メッセージID */
    private String id;
    
    /** メッセージテキスト */
    private String text;
    
    /**
     * メッセージIDを取得する.
     * @return メッセージID
     */
    @XmlAttribute
    public String getId() {
        return id;
    }
    
    /**
     * メッセージIDを設定する.
     * @param id メッセージID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * メッセージテキストを取得する.
     * @return　メッセージテキスト
     */
    public String getText() {
        return text;
    }

    /**
     * メッセージテキストを設定する.
     * @param text メッセージテキスト
     */
    public void setText(String text) {
        this.text = text;
    }
}
