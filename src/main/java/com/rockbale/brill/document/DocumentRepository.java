package com.rockbale.brill.document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hackeanwarley on 12/20/2016.
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>{

    List<Document> findByIdIn(Long[] ids);

}
