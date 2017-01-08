package com.rockbale.brill.document;

import com.rockbale.brill.document.request.DocumentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static com.rockbale.brill.document.Document.DocumentBuilder;

/**
 * Created by hackeanwarley on 12/20/2016.
 */
@Service
public class DocumentService {

    @Value("${brill.repository.doc}")
    String documentsPath;

    @Value("${brill.repository.img}")
    String imagesPath;

    @Value("${brill.search.notify.url}")
    String searchUrl;

    @Autowired
    private DocumentRepository documentRepository;

    public void addNewDocument(DocumentRequest documentRequest) {
        Document d = new DocumentBuilder()
                                .withTitle(documentRequest.getTitle())
                                .withDescription(documentRequest.getDescription())
                                .build();
        documentRepository.save(d);
        try {
            MultipartFile document = documentRequest.getDocument();
            MultipartFile coverImage = documentRequest.getCoverImage();
            String filename = String.format("%s\\%d.%s", documentsPath, d.getId(), getFileExtension(document.getOriginalFilename()));
            String coverImg =  String.format("%s\\%d.%s", imagesPath, d.getId(), getFileExtension(coverImage.getOriginalFilename()));
            d.setFileName(filename);
            d.setCoverImage(d.getId() + "." + getFileExtension(coverImage.getOriginalFilename()));
            documentRepository.save(d);
            write(document, filename );
            write(coverImage,coverImg);
            HttpEntity<Document> httpEntity = new HttpEntity<>(d);
            RestTemplate rest = new RestTemplate();
            rest.postForEntity(searchUrl, httpEntity, String.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Document findById(Long id){
        return documentRepository.findOne(id);
    }

    private void write(MultipartFile file, String path) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
        bos.write(file.getBytes());
        bos.close();
        fileOutputStream.close();
    }

    private String getFileExtension(String name){
        int dotIndex = name.lastIndexOf('.');
        return name.substring(dotIndex + 1);
    }

    public List<Document> findByIdIn(Long[] ids) {
        return documentRepository.findByIdIn(ids);
    }

    public List<Document> findAll() {
        return documentRepository.findAll();
    }
}
