package com.envers;

import org.springframework.data.repository.history.RevisionRepository;

public interface BookStoreRevisionRepository extends RevisionRepository<BookStore, Long, Long> {
}
