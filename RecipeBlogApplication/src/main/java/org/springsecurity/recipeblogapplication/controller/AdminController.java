package org.springsecurity.recipeblogapplication.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springsecurity.recipeblogapplication.entity.Category;
import org.springsecurity.recipeblogapplication.entity.Post;
import org.springsecurity.recipeblogapplication.service.CategoryService;
import org.springsecurity.recipeblogapplication.service.PostService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private CategoryService categoryService;
    private PostService postService;
    private static String UPLOAD_DIR = "src/main/resources/static/img/recipes/";

    @Autowired
    public AdminController(CategoryService categoryService, PostService postService) {
        this.categoryService = categoryService;
        this.postService = postService;
    }

    @GetMapping({"", "/", "/dashboard"})
    public String dashboard(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("adminName", principal.getName());
        }
        return "admin/dashboard";
    }

    @GetMapping("/categories")
    public String listCategories(Model model, @RequestParam(required = false) Integer editId) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("newCategory", new Category());
        model.addAttribute("editId", editId);
        return "admin/categories";
    }

    @PostMapping("/categories")
    public String createCategories(@ModelAttribute("newCategory") Category category, HttpSession session) {
        if (categoryService.existsByName(category.getName())) {
            session.setAttribute("errorMsg", "Категория с таким названием уже существует");
            return "redirect:/admin/categories";
        }
        categoryService.save(category);
        session.setAttribute("sucMsg", "Категория успешно добавлена");
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/edit/{id}")
    public String editCategory(@PathVariable Integer id, Model model) {
        Optional<Category> categoryOpt = categoryService.findById(id);

        if (categoryOpt.isEmpty()) {
            return "redirect:/admin/categories?error=notfound";
        }
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("newCategory", new Category());
        model.addAttribute("editId", id);
//        model.addAttribute("editCategory", categoryOpt.get());

        return "admin/categories";
    }

    @PostMapping("/categories/update")
    public String updateCategory(@ModelAttribute Category category, HttpSession session) {
        Optional<Category> existing = categoryService.findById(category.getId());

        if (existing.isEmpty()) {
            session.setAttribute("errorMsg", "Категория не найдена");
            return "redirect:/admin/categories";
        }
        if (!existing.get().getName().equals(category.getName()) &&
                categoryService.existsByName(category.getName())) {
            session.setAttribute("errorMsg", "Такое название уже используется");
            return "redirect:/admin/categories/edit/" + category.getId();
        }

        categoryService.save(category);
        session.setAttribute("sucMsg", "Категория обновлена");
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable Integer id, HttpSession session) {
        if (categoryService.findById(id).isPresent()) {
            categoryService.deleteById(id);
            session.setAttribute("sucMsg", "Категория удалена");
        } else {
            session.setAttribute("errorMsg", "Ошибка удаления категории");
        }
        return "redirect:/admin/categories";
    }

    @PostMapping("/posts/save")
    public String savePost(@RequestParam(required = false) Integer id,
                           @RequestParam String title,
                           @RequestParam String content,
                           @RequestParam(value = "file", required = false) MultipartFile file,
                           @RequestParam(value = "categoryId", required = false) Integer categoryId,
                           HttpSession session) throws IOException {

        session.removeAttribute("sucMsg");
        session.removeAttribute("errorMsg");

        if (categoryId == null || categoryId <= 0) {
            session.setAttribute("errorMsg", "Пожалуйста, выберите категорию");
            return "redirect:/admin/posts/new";
        }

        Optional<Category> categoryOpt = categoryService.findById(categoryId);
        if (categoryOpt.isEmpty()) {
            session.setAttribute("errorMsg", "Выбранная категория не существует");
            return "redirect:/admin/posts/new";
        }
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCreatedAt(LocalDateTime.now());
        post.setCategory(categoryOpt.get());

            if (file != null && !file.isEmpty()) {
                try {
                    String originalFileName = file.getOriginalFilename();
                    String extension = "";
                    if (originalFileName != null && originalFileName.contains(".")) {
                        extension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    }
                    String uniqueFileName = UUID.randomUUID() + extension;
                    Path uploadPath = Paths.get(UPLOAD_DIR);
                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }
                    file.transferTo(uploadPath.resolve(uniqueFileName));
                    post.setImage(uniqueFileName);
                } catch (IOException e) {
                    session.setAttribute("errorMsg", "Ошибка при сохранении файла: " + e.getMessage());
                    return "redirect:/admin/posts/new";
                }
            }
            try {
                postService.save(post);
                session.setAttribute("sucMsg", "Рецепт успешно сохранён");
                return "redirect:/admin/posts";
            } catch (Exception e) {
                session.setAttribute("errorMsg", "Ошибка при сохранении рецепта: " + e.getMessage());
                return "redirect:/admin/posts/new";
            }
    }

    @GetMapping("/posts")
    public String listPosts(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "admin/posts";
    }

    @GetMapping("/users")
    public String users() {
        return "admin/users";
    }

    @GetMapping("/posts/new")
    public String newPost(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("categories", categoryService.findAll());
        return "admin/post-form";
    }

    @GetMapping("/posts/edit/{id}")
    public String editPost(@PathVariable Integer id, Model model, HttpSession session) {
        Optional<Post> postOpt = postService.findById(id);
        if (postOpt.isEmpty()) {
            session.setAttribute("errorMsg", "Рецепт не найден");
            return "redirect:/admin/posts";
        }
        model.addAttribute("post", postOpt.get());
        model.addAttribute("categories", categoryService.findAll());
        return "admin/post-form";
    }
}