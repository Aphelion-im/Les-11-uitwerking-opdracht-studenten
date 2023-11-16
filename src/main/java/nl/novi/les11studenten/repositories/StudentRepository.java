// Amigoscode on Repositories and JPA: https://youtu.be/8SGI_XS5OPw?si=q75qET2ekp7pMMUx&t=2925 (Amigoscode, Spring Boot Tutorial | Spring Data JPA | 2021)
package nl.novi.les11studenten.repositories;

import nl.novi.les11studenten.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> { // Class name, Long ID

    // https://medium.com/@hashanlahiru6/all-about-query-methods-in-spring-data-jpa-52d74e5d2be7
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
    // Mogelijk een Repository Query Return Type (List<Student>)
    List<Student> findByFullNameContainingIgnoreCase(String substring);
}
