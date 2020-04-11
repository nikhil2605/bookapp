package com.example.demo.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@RequestMapping(value = "/api")
@RestController
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	@RequestMapping(value = "/new",method = RequestMethod.GET)
	public List<Book> addSampleData(){
		Book b1 = new Book("Maths1",456.50);
		Book b2 = new Book("Maths2",456.50);
		Book b3 = new Book("Maths3",456.50);
		List<Book> list = Arrays.asList(b1,b2,b3);
		bookRepository.saveAll(list);
		return bookRepository.findAll();
		
	}

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public List<Book> getAllBooks() {
		List<Book> list = bookRepository.findAll();
		return list;
	}

	@RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
	public Book getOneBook(@PathVariable(value = "id") Long id) {
		return bookRepository.getOne(id);
	}

	@RequestMapping(value = "/books", method = RequestMethod.POST)
	public ResponseEntity<String> saveBook(@Valid @RequestBody Book newBook) {
		ResponseEntity<String> response = null;
		bookRepository.save(newBook);
		response = new ResponseEntity<String>("New Book Added.", HttpStatus.ACCEPTED);
		return response;
	}

	@RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateBook(@PathVariable(value = "id") Long id, @Valid @RequestBody Book newBook) {
		Book updateBook = bookRepository.getOne(id);
		ResponseEntity<String> response = null;
		if (updateBook == null) {
			response = new ResponseEntity<String>("Book Not Found", HttpStatus.NOT_FOUND);
		} else {
			updateBook.setBookName(newBook.getBookName());
			updateBook.setBookPrice(newBook.getBookPrice());
			bookRepository.save(updateBook);
			response = new ResponseEntity<String>("Book:" + id + " updated", HttpStatus.ACCEPTED);
		}
		return response;
	}

	@RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBook(@PathVariable(value = "id") Long id) {
		Book deleteBook = bookRepository.getOne(id);
		ResponseEntity<String> response = null;
		if (deleteBook == null) {
			response = new ResponseEntity<String>("Book Not Found", HttpStatus.NOT_FOUND);
		} else {
			bookRepository.deleteById(id);
			response = new ResponseEntity<String>("Book:" + id + " deleted", HttpStatus.ACCEPTED);
		}
		return response;
	}
}
