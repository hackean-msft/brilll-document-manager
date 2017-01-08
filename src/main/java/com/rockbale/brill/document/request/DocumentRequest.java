package com.rockbale.brill.document.request;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by hackeanwarley on 12/20/2016.
 */
public class DocumentRequest {

    private String title;
    private String description;
    private MultipartFile coverImage;
    private MultipartFile document;

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

    public MultipartFile getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(MultipartFile coverImage) {
        this.coverImage = coverImage;
    }

    public void setDocument(MultipartFile document) {
        this.document = document;
    }

    public MultipartFile getDocument() {
        return document;
    }
}
