package com.rockbale.brill.document;

import javax.persistence.*;

/**
 * Created by hackeanwarley on 12/20/2016.
 */
@Entity
public class DocumentMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String key;
    private String value;
    @ManyToOne
    private Document document;

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
