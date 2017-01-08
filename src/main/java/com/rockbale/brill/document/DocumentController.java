package com.rockbale.brill.document;

import com.rockbale.brill.document.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hackeanwarley on 12/20/2016.
 */
@RestController
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(@ModelAttribute DocumentRequest document) {
        documentService.addNewDocument(document);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Document getById(@PathVariable("fileName") Long id) {
        return documentService.findById(id);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/documents", method = RequestMethod.POST)
    public List<Document> getDocumentsById(@RequestBody Long[]ids) {
        return documentService.findByIdIn(ids);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/documents", method = RequestMethod.GET)
    public List<Document> getAllDocuments() {
        return documentService.findAll();
    }
}
