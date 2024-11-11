package com.example.bookstore.controller;
import com.example.bookstore.model.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.service.BookService;

@RestController
@RequestMapping("/api/books")
public class HomeController {
	@Autowired
	private BookService bs;
	
	@GetMapping
	public List<Book> getBooks()
	{
		return bs.getBooks();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Book> getByid(@PathVariable Long id)
	{
		Optional<Book> book = bs.getById(id);
		return book.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
	}
	@PostMapping("/{id}")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
	    Book savedBook = bs.saveBook(book);
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book)
	{
		if(!bs.getById(id).isPresent())
		{
			return ResponseEntity.notFound().build();
		}
		book.setId(id);
		Book updatedBook = bs.saveBook(book);
		return ResponseEntity.ok(updatedBook);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBok(@PathVariable Long id)
	{
		if(!bs.getById(id).isPresent())
		{
			return ResponseEntity.notFound().build();
		}
		bs.deleteBook(id);
		return ResponseEntity.noContent().build();
	}
}
