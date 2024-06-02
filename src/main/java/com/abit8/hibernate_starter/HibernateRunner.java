package com.abit8.hibernate_starter;

import com.abit8.hibernate_starter.entity.Company;
import com.abit8.hibernate_starter.entity.PersonalInfo;
import com.abit8.hibernate_starter.entity.Role;
import com.abit8.hibernate_starter.entity.User;
import com.abit8.hibernate_starter.util.HibernateUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
public class HibernateRunner {
    public static void main(String[] args) {
        Company company = Company.builder()
                .name("Yandex")
                .build();
        User user = User.builder()
                .username("Ishenaly@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Ishen")
                        .lastname("Ishenov")
                        .birthDate(LocalDate.of(2007, 02, 03)).build())
                .age(17)
                .role(Role.ADMIN)
                .company(company)
                .build();

        try (var sessionFactory = HibernateUtils.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.saveOrUpdate(user);

            session.getTransaction().commit();
        }
    }
}