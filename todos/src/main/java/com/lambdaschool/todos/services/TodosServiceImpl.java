package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todo;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "todosService")
public class TodosServiceImpl implements TodosService{

    @Autowired
    private TodoRepository todorepos;

    @Autowired
    private UserService userService;

    @Override
    public void markComplete(long todoid) {
        Todo currentTodo = todorepos.findById(todoid)
                .orElseThrow(() -> new EntityNotFoundException("Todo " + todoid + " not found"));
        currentTodo.setCompleted(true);

    }

    @Override
    public Todo save(long userid, Todo todo) {
        User user = userService.findUserById(userid);
        Todo newTodo = new Todo(user, todo.getDescription());
        todorepos.save(newTodo);
        return newTodo;
    }
}
