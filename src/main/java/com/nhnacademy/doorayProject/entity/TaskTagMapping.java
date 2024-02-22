package com.nhnacademy.doorayProject.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/*
CREATE TABLE dooray_task_tag_mapping
(
    task_id int     NOT NULL,
    tag_id  Integer NOT NULL,
    primary key (task_id, tag_id),
    constraint dooray_task_tag_mapping_task_id_fk foreign key (task_id) references dooray_task (task_id),
    constraint dooray_task_tag_mapping_tag_id_fk foreign key (tag_id) references dooray_tag (tag_id)

 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "dooray_task_tag_mapping")
public class TaskTagMapping {
    @EmbeddedId
    private Pk pk;

    @MapsId("taskId")
    @JoinColumn(name = "task_id")
    @ManyToOne
    private Task task;

    @MapsId("tagId")
    @JoinColumn(name = "tag_id")
    @ManyToOne
    private Tag tag;



    @Embeddable
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Pk implements Serializable {

        private Integer taskId;


        private Integer tagId;
    }
}
