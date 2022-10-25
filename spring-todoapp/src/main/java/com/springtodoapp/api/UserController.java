package com.springtodoapp.api;

import com.springtodoapp.entities.TodoItem;
import com.springtodoapp.entities.User;
import com.springtodoapp.repository.TodoItemRepository;
import com.springtodoapp.repository.UserRepository;
import com.springtodoapp.request.AddTodoItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final TodoItemRepository todoItemRepository;

    @GetMapping("{userId}")
    public User getUserById(@PathVariable Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
    }

    @PostMapping("{userId}/todos")
    public void addTodoItem(@PathVariable Long userId , @RequestBody AddTodoItemRequest addTodoItemRequest){
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        TodoItem todoItem = new TodoItem();
        todoItem.setDescription(addTodoItemRequest.getDescription());
        user.getTodoItems().add(todoItem);
        todoItemRepository.save(todoItem);
        userRepository.save(user);
    }

    @PostMapping("todos/{todoItemId}")
    public void toggleTodoItemCompleted(@PathVariable Long todoItemId){
        TodoItem todoItem = todoItemRepository.findById(todoItemId).orElseThrow(()->new NoSuchElementException());
        todoItem.setCompleted(!todoItem.getCompleted());
        todoItemRepository.save(todoItem);
    }
    @DeleteMapping("{userId}/todos/{todoItemId}")
    public void deleteTodo(@PathVariable Long userId,@PathVariable Long todoItemId){
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        TodoItem todoItem = todoItemRepository.findById(todoItemId).orElseThrow(()->new NoSuchElementException());
        user.getTodoItems().remove(todoItem);
        todoItemRepository.delete(todoItem);
    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        userRepository.delete(user);
    }

}