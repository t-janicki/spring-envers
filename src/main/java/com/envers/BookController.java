package com.envers;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@RestController
@Log4j2
public class BookController {

    private final BookRepository bookRepository;
    private final BookRevisionRepository bookRevisionRepository;
    private final BookStoreRepository bookStoreRepository;

    public BookController(BookRepository bookRepository,
                          BookRevisionRepository bookRevisionRepository,
                          BookStoreRepository bookStoreRepository) {
        this.bookRepository = bookRepository;
        this.bookRevisionRepository = bookRevisionRepository;
        this.bookStoreRepository = bookStoreRepository;
    }

    @PostMapping(value = "/bookstore")
    public BookStore createBookstore() {
        BookStore newBookStore = new BookStore();
        newBookStore.setName("Bookstore");
        return bookStoreRepository.save(newBookStore);
    }

    @PostMapping(value = "/book/bookstoreId/{bookstoreId}")
    public Book saveBook(@RequestBody Book book, @PathVariable Long bookstoreId) {
        Book newBook = bookRepository.save(book);

        BookStore bookStore = bookStoreRepository.findById(bookstoreId).get();
        bookStore.getBooks().add(book);
        bookStoreRepository.save(bookStore);

        return newBook;
    }

    @PutMapping(value = "/book/{id}/{pages}")
    public String updateBook(@PathVariable(name = "id") Long id, @PathVariable(name = "pages") Integer pages) {
        Book book = bookRepository.findById(id).get();

        book.setPages(pages);
        bookRepository.save(book);
        return "Book updated";
    }

    @DeleteMapping(value = "/book")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "Book deleted";
    }

    @GetMapping(value = "/book/revision/{id}")
    public Book getInfo(@PathVariable Long id) {

        Revisions<Long, Book> test = bookRepository.findRevisions(id);
        Book book = test.stream().map(Revision::getEntity).findFirst().get();
        System.out.println(test.stream().map(v -> v.getMetadata().getRevisionNumber().get()));
        System.out.println();
        System.out.println(bookRevisionRepository.findLastChangeRevision(id));
        return book;
    }
}
