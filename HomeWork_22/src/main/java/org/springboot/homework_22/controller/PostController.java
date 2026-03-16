package org.springboot.homework_22.controller;

import org.springboot.homework_22.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PostController {
    private List<Post> posts = new ArrayList<>();

    public  PostController(){
        posts.add(new Post(1, "Java для начинающих",
                "Полное руководство по Java для новичков...", "Программирование", "Иван Петров"));
        posts.add(new Post(2, "Thymeleaf шаблоны",
                "Как использовать Thymeleaf в Spring Boot...", "Веб-разработка", "Анна Смирнова"));
        posts.add(new Post(3, "Spring Boot основы",
                "Создание REST API с Spring Boot...", "Программирование", "Петр Сидоров"));
        posts.add(new Post(4, "Путешествие в Париж",
                "Мои впечатления о поездке в Париж...", "Путешествия", "Елена Иванова"));
        posts.add(new Post(5, "Здоровое питание",
                "Советы по правильному питанию...", "Здоровье", "Мария Козлова"));
    }

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/post/{id}")
    public String showPost(@PathVariable int id, Model model){
        Post post = posts.stream()
                .filter(p -> p.getId() == id)
                .findFirst().orElse(null);

        if(post != null){
            model.addAttribute("post", post);


        if("Программирование".equals(post.getCategory())){
            model.addAttribute("relatedLink", "/category?cat=Программирование");
            model.addAttribute("relatedText", "Другие статьи по программированию");
        } else if("Путешествия".equals(post.getCategory())){
            model.addAttribute("relatedLink", "/category?cat=Путешествия");
            model.addAttribute("relatedText", "Другие истории о путешествиях");
        } else{
            model.addAttribute("relatedLink", "/");
            model.addAttribute("relatedText", "Вернуться ко всем записям");
        }
        return "post";
        } else{
            return "index";
        }
    }

    @GetMapping("/category")
    public String filterByCategory(@RequestParam(required = false) String cat, Model model) {
        if (cat != null && !cat.isEmpty()) {
            List<Post> filteredPosts = posts.stream()
                    .filter(post -> post.getCategory().equalsIgnoreCase(cat))
                    .collect(Collectors.toList());
            model.addAttribute("posts", filteredPosts);
            model.addAttribute("selectedCategory", cat);
        } else {
            model.addAttribute("posts", posts);
        }
        return "index";
    }
}
