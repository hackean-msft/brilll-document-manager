package com.rockbale.brill.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hackeanwarley on 12/17/2016.
 */
@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    private String description;

    @JsonProperty("filename")
    private String fileName;

    @JsonProperty("cover_img")
    private String coverImage;
    private Date uploaded;

    @Transient
    @JsonIgnore
    private Map<String, String> meta;

    public Document() {
        meta = new HashMap<>();
    }

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {}

    private Document(String title, String description, Date uploaded) {
        this.title = title;
        this.description = description;
        this.uploaded = uploaded;
        this.meta = new HashMap<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUploaded() {
        return uploaded;
    }

    public void setUploaded(Date uploaded) {
        this.uploaded = uploaded;
    }

    public void AddMeta(String key, String value) {
        this.meta = meta;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public Map<String, String> getMeta() {
        return meta;
    }

+  public static class DocumentBuilder {

        private String title;
        private String description;

        public DocumentBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public DocumentBuilder withDescription(String description){
            this.description = description;
            return this;
        }

        public Document build(){
            return new Document(this.title, this.description, new Date());
        }
    }
}
