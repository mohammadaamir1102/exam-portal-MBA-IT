package com.exam;

import com.exam.helper.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.QuizRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamPortalApplication {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public QuizRepository quizRepository;

    public static void main(String[] args) {
        SpringApplication.run(ExamPortalApplication.class, args);
    }


//    @PostConstruct
    public void adminAccountCreation() {
        try {


            System.out.println("starting code");

            User user = new User();

            user.setFirstName("Aamir");
            user.setLastName("Khan");
            user.setUsername("aamir123");
            user.setPassword(this.bCryptPasswordEncoder.encode("aamir123"));
            user.setEmail("aamir@gmail.com");
            user.setProfile("default.png");

            Role role1 = new Role();
            role1.setRoleId(46L);
//            role1.setRoleName("NORMAL");
            role1.setRoleName("ADMIN");

            Set<UserRole> userRoleSet = new HashSet<>();
            UserRole userRole = new UserRole();

            userRole.setRole(role1);

            userRole.setUser(user);

            userRoleSet.add(userRole);

            User user1 = userService.createUser(user, userRoleSet);
            System.out.println(user1.getUsername());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

