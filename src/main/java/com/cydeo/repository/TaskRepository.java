package com.cydeo.repository;

import com.cydeo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task,Long>
{
    @Query("Select COUNT(t) from Task T WHERE T.project.projectCode = ?1 AND t.taskStatus <>'Completed'")
    int totalNonCompletedTask(String projectCode);


    @Query(value = "SELECT COUNT(*)" +
            "FROM tasks t JOIN projects p on t.project_id = p.id " +
            "WHERE p.project_code = ?1 AND t.task_status = 'Complete'",nativeQuery = true)
    int totalCompletedTask(String projectCode);

}
