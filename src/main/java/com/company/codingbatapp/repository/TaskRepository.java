package com.company.codingbatapp.repository;

import com.company.codingbatapp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);

    boolean existsByCategory_IdAndName(Integer category_id,
                                       @NotNull(message = "bo'sh bo'lmasin")
                                       @NotEmpty(message = "Empty bo'lmasin") String name);
     boolean existsByCategory_IdAndNameAndIdNot(Integer category_id,
                                                @NotNull(message = "bo'sh bo'lmasin")
                                                @NotEmpty(message = "Empty bo'lmasin")
                                                        String name, Integer id);


}
