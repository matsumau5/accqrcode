package com.product.acc.domain.sys.domain.sys.domain.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.context.annotation.Bean;

/**
 * MessageConfig's Bean Class
 * @author inomata
 *@version $Revision$
 */
public class MessageConfig {

    /** メッセージマップ */
    Map<String, String> messageMap;
    
    /** 置換パターン */
    private static final String REPLACE_PATTERN = "[{][0-9][}]";
    
    /** 固定文言キー */
    private static final String FIXED_MESSAGE_KEY = "";
    
    /**
     * MessageConfigのキーからメッセージを取得し、返却する.
     * @param key キー
     * @return メッセージ
     */
    private String getMessage(String key) {
        if (this.messageMap.containsKey(key)) {
            return this.messageMap.get(key);
        } else {
            throw new IllegalArgumentException("MessageConfig.xmlに存在しないキーが指定されました。　key:" + key);
        }
    }
    
    /**
     * メッセージを取得する<br>
     * wordsのindexと取得メッセージのパラメータ埋め込み箇所の数があっていることに注意
     * @param key メッセージキー
     * @param words 置換文字
     * @return メッセージ
     */
    public String getMeeage(String key, Object... words) {
        String message = this.getMessage(key);
        Pattern pattern = Pattern.compile(REPLACE_PATTERN);
        Matcher matcher = pattern.matcher(message);
        
        for (Object word : words) {
            if (matcher.find()) {
                message = message.replace(matcher.group(), word.toString());
            }
        }
        return message;
    }
    
    /**
     * バリデーションエラーのメッセージを取得する際に使用するメソッド.
     * @param key キー
     * @return メッセージ
     */
    public String getValidationMessage(String key) {
        return this.getMeeage(key);
    }
    
    /**
     * バリデーションエラー時に出力する固定文言を取得する.
     * @return メッセージ
     */
    public String getFixedMessage() {
        return this.getMessage(FIXED_MESSAGE_KEY);
    }
    
    /**
     * メッセージリソースファイルを読み込み、ビーンとして登録するメソッド.
     * @return メッセージコンフィグビーン
     * @throws IOException
     */
    @XmlElement(name="JJAMessageConfig")
    @Bean
    public JJAMessageConfig setMessageList() throws IOException {
        try (InputStream is = this.getClass().getResourceAsStream("/MessageConfig.xml")) {
            JJAMessageConfig mes = JAXB.unmarshal(is, JJAMessageConfig.class);
            List<Message> messageList = mes.getMessage();
            this.messageMap = toMap(messageList);
            return mes;
        } catch (IllegalArgumentException e) {
            throw new DataBindingException("MessageConfig.xmlが見つかりません。", e);
        }
    }
    
    /**
     * xmlから取得してリスト化したメッセージをマップ化してキーを渡せば値が取れるようにする.
     * @param messageList メッセージリスト
     * @return メッセージマップ
     */
    private Map<String, String> toMap(List<Message> messageList) {
        Map<String, String> convertMap = new HashMap<>();
        messageList.forEach(message -> convertMap.put(message.getId(), message.getText()));
        return convertMap;
    }
}
