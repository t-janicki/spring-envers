package com.envers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookStoreRepository extends JpaRepository<BookStore, Long> {

    Optional<BookStore> findByName(String name);
}
