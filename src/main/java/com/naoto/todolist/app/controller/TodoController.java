package com.naoto.todolist.app.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.naoto.todolist.domain.model.TodoList;
import com.naoto.todolist.domain.repository.TodoRepository;




@Controller
public class TodoController {


    private final TodoRepository repository;
    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String index(@ModelAttribute TodoList todoList , Model model) {
        model.addAttribute("people", repository.findAll());
        return "todo/index";
    }


    @PostMapping("/create")
    public String create(@Validated @ModelAttribute TodoList todoList, BindingResult result , Model model) {

        if (result.hasErrors()){
            model.addAttribute("people", repository.findAll());
            return "todo/index";
        }
        repository.saveAndFlush(todoList);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String remove(@PathVariable long id) {
        repository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("todoList", repository.findById(id));
        return "todo/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable long id, @Validated @ModelAttribute TodoList todoList, BindingResult result) {
        if(result.hasErrors()){
            return "todo/edit";
        }
        repository.save(todoList);
        return "redirect:/";
    }


  @PostConstruct
  public void dataInit(){
    TodoList run = new TodoList();
    run.setContent("走る");
    run.setTodo(true);
    repository.saveAndFlush(run);
 
    TodoList swim = new TodoList();
    swim.setContent("泳ぐ");
    swim.setTodo(true);
    repository.saveAndFlush(swim);
 
   }

   
}
