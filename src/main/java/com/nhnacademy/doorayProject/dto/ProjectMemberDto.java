package com.nhnacademy.doorayProject.dto;

import lombok.Data;


/**
 *  프로젝트의 소유자 = master
 *  피 추가자 = slave
 */
@Data
public class ProjectMemberDto {

    String master;

    String slave;
}
