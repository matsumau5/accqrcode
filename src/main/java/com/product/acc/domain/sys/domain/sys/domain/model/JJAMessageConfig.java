package com.product.acc.domain.sys.domain.sys.domain.model;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * JJAMessageConfig Bean Class
 * @author inomata
 *@version $Revision$
 */
public class JJAMessageConfig {

    /** メッセージ */
    private List<Message> message;
    
    /**
     * メッセージを取得する.
     * @return メッセージ
     */
    @XmlElement(name="Message")
    public List<Message> getMessage() {
        return message;
    }
    
    /**
     * メッセージを設定する.
     * @param message メッセージ
     */
    public void setMessage(List<Message> message) {
        this.message = message;
    }
}
