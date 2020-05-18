package com.envers;

import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRevisionRepository extends RevisionRepository<Book, Long, Long> {

}
