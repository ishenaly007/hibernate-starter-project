import com.abit8.hibernate_starter.entity.*;
import com.abit8.hibernate_starter.util.HibernateUtils;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;

public class HibernateRunnerTest {

    @Test
    public void checkManyToMany() {
        @Cleanup var sessionFactory = HibernateUtils.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        Chat chat = session.get(Chat.class, 1);
        User user = session.get(User.class, 5);
        UserChat userChat = UserChat.builder()
                .createdAt(Instant.now())
                .createdBy("Johny")
                .build();
        userChat.setChat(chat);
        userChat.setUser(user);
        session.save(userChat);
        session.getTransaction().commit();
    }

    @Test
    public void checkOneToOne() {
        @Cleanup var sessionFactory = HibernateUtils.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        Profile profile = Profile.builder()
                .language("RU")
                .street("Pobedy 1")
                .build();
        User user = User.builder()
                .username("Ishennarkozuev000@gmail.com").build();
        session.save(user);
        profile.setUser(user);
        session.save(profile);

        session.getTransaction().commit();
    }

    @Test
    public void orphalRemomal() {
        @Cleanup var sessionFactory = HibernateUtils.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var company = session.get(Company.class, 1);
        company.getUsers().removeIf(user -> user.getId().equals(1));

        session.getTransaction().commit();
    }

    @Test
    public void addCompanyAndUsers() {
        @Cleanup var sessionFactory = HibernateUtils.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        Company company = Company.builder()
                .name("Amazon")
                .build();
        User user = User.builder()
                .username("Ishenaly1@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Ishen")
                        .lastname("Ishenov")
                        .birthDate(LocalDate.of(2007, 02, 03)).build())
                .age(17)
                .role(Role.ADMIN)
                .build();

        session.beginTransaction();

        company.addUser(user);
        session.save(company);

        session.getTransaction().commit();
    }

    @Test
    public void checkOneToMany() {
        @Cleanup var sessionFactory = HibernateUtils.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var company = session.get(Company.class, 1);
        System.out.println(company.getUsers());

        session.getTransaction().commit();
    }
}