package com.example.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookstore.dao.BookRepository;
import com.example.bookstore.model.Book;

@Service
public class BookService {
	@Autowired
	private BookRepository br;
	
	public List<Book> getBooks()
	{
		return br.findAll();
	}
	public Optional<Book> getById(Long id)
	{
		return br.findById(id);
	}
	public Book saveBook(Book book)
	{
		return br.save(book);
	}
	public void deleteBook(Long id)
	{
		 br.deleteById(id);
	}
}
