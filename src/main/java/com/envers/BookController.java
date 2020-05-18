package com.envers;

import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController(value = "/api")
public class BookController {

    private final BookRepository bookRepository;
    private final BookRevisionRepository bookRevisionRepository;

    public BookController(BookRepository bookRepository,
                          BookRevisionRepository bookRevisionRepository) {
        this.bookRepository = bookRepository;
        this.bookRevisionRepository = bookRevisionRepository;
    }

    @PostMapping(value = "/book")
    public Book saveBook(@RequestBody Book book) {
        return bookRepository.save(book);
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
