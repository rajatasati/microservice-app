package com.dailylearn.user.service;

import com.dailylearn.user.VO.Department;
import com.dailylearn.user.VO.ResponseTemplateVO;
import com.dailylearn.user.entity.User;
import com.dailylearn.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        Department department = restTemplate
                .getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId()
                        , Department.class);
        vo.setUser(user);
        vo.setDepartment(department);

        return vo;
    }

    public List<ResponseTemplateVO> getAllUsersWithDepartment() {
        log.info("Inside getAllUsersWithDepartment of UserService");
        List<ResponseTemplateVO> userListResponse = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            ResponseTemplateVO vo = new ResponseTemplateVO();
            Department department = restTemplate
                    .getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId()
                            , Department.class);
            vo.setUser(user);
            vo.setDepartment(department);
            userListResponse.add(vo);
        }
        return userListResponse;
    }
}
