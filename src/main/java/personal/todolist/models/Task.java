package personal.todolist.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tasks")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "is_completed")
    private boolean isCompleted;


    public Task setId(Long id) {
        this.id = id;
        return this;
    }

    public Task setName(String name) {
        this.name = name;
        return this;
    }

    public Task setCompleted(boolean completed) {
        isCompleted = completed;
        return this;
    }

}
