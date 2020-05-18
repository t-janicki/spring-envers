package com.envers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends RevisionRepository<Book, Long, Long>, JpaRepository<Book, Long> {


}
