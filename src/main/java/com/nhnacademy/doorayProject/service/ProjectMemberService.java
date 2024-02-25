package com.nhnacademy.doorayProject.service;

public interface ProjectMemberService {
    void deleteProjectMember(String slave, Integer projectId);

    public enum Auth {
        ADMIN, MEMBER
    }
    void addProjectMember(String  userId, Integer projectId, String auth);
    boolean checkAuth(String userId, Integer projectId);
}
