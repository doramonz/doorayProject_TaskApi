package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.entity.Comment;
import com.nhnacademy.doorayProject.entity.Project;
import com.nhnacademy.doorayProject.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
public class CommentRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CommentRepository commentRepository;

    private Task task;

    @BeforeEach
    void setUp(){
        Project project = new Project(1123,"11","qweqerw");
        task = new Task();
        task.setTaskId(1231);
        task.setProject(project);
        task.setUserId("1234");
        task.setTaskTitle("wow");
        task.setTaskContent("qwer");
        task.setCreateAt(LocalDateTime.now());
        task.setStatus(Task.Status.진행중);
        testEntityManager.merge(task);

        Comment comment1 = new Comment();
        comment1.setUserId("1");
        comment1.setCommentContent("qwert");
        comment1.setTask(task);
        testEntityManager.persist(comment1);

        Comment comment2 = new Comment();
        comment2.setUserId("2");
        comment2.setCommentContent("12ewr");
        comment2.setTask(task);
        testEntityManager.persist(comment2);
    }

    @Test
    void testFindAllByTask_TaskId() {
        Integer taskId = 1231;
        List<Comment> commentList = commentRepository.findAllByTask_TaskId(taskId);
        assertThat(commentList).hasSize(2);
    }

}
