package org.spring;
import org.spring.entity.Book;
import org.spring.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private IBookService bookService;

    @RequestMapping("/home")
    public String home(Model model){
        return "home";
    }

    @RequestMapping(path = "/addBook", method = RequestMethod.POST)
    public String addBook(@ModelAttribute Book book, Model model){
        bookService.addBook(book);
        model.addAttribute("msg", "The book was added successfully");
        model.addAttribute("book", book);
        return "book";
    }
}
