package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.entity.Comment;
import com.nhnacademy.doorayProject.entity.Project;
import com.nhnacademy.doorayProject.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CommentRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CommentRepository commentRepository;

    private Task task;

    @BeforeEach
    void setUp(){
        Project project = new Project();
        project.setName("11");
        project.setStatus("ok");
        testEntityManager.persist(project);

        task = new Task();
        task.setProject(project);
        task.setUserId("1234");
        task.setTaskTitle("wow");
        task.setTaskContent("qwer");
        task.setCreateAt(LocalDateTime.now());
        task.setStatus(Task.Status.진행중);
        testEntityManager.persist(task);

        Comment comment1 = new Comment();
        comment1.setUserId("1");
        comment1.setCommentContent("qwert");
        comment1.setTask(task);
        testEntityManager.persistAndFlush(comment1);

        Comment comment2 = new Comment();
        comment2.setUserId("2");
        comment2.setCommentContent("12ewr");
        comment2.setTask(task);
        testEntityManager.persistAndFlush(comment2);
    }

    @Test
    void testFindAllByTask_TaskId() {
        Integer taskId = task.getTaskId();
        List<Comment> commentList = commentRepository.findAllByTask_TaskId(taskId);
        assertThat(commentList).hasSize(2);
    }

}
