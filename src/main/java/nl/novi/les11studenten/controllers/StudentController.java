package nl.novi.les11studenten.controllers;

import nl.novi.les11studenten.models.Student;
import nl.novi.les11studenten.repositories.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    /*
    Om onze repository in onze controller te kunnen gebruiken, gebruiken we geen “new”  keyword, maar laten we Spring
    de juiste Bean vinden door middel van dependency injection.
    Dependency injection is heel simpel. Wanneer we in onze controller gebruik willen maken van onze repository,
    dan hoeven dat alleen maar aan te geven. We declareren een globale variabele en vullen die in, in de constructor.
    Note: We kunnen dependencies ook injecteren zonder de constructor te gebruiken. Dit kun je doen door de @Autowired annotatie
    te gebruiken boven de klasse variabele of boven de getter van die klassevariabele.
    Constructor injection heeft echter de voorkeur, omdat je de klasse variabele dan "final” kunt maken.
    Zo weet je zeker dat de repository (in dit geval) niet gaandeweg gewijzigd kan worden.
    https://edhub.novi.nl/study/courses/319/content/12890 (Spring context en dependency injection)
     */
    private final StudentRepository studentRepository;

    // Lijkt er op dat dependency injection het meegeven van een object als een parameter in de constructor van een andere klasse is.
    // To promote loose coupling
    // Actual Constructor injection:
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    // findAll() hoort bij StudentRepository/JpaRepository en bijhorende methods
    public ResponseEntity<List<Student>> getStudents() {
        // return ResponseEntity.ok(studentRepository.findAll());
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }

    // Waarom een Optional als return value in een POST request? Waarschijnlijk omdat het nog niet duidelijk is wat er gaat worden meegegeven qua data type/info
    // Dit bevat nog geen validatie en NullPointerException beveiliging.
    // Getters en setters vereist (Student.java) om een JSON object te posten en terug te krijgen!
    // Bij elke ResponseEntity.created() een URI uri meegeven.
    @PostMapping
    public ResponseEntity<?> postStudent(@RequestBody Student student) { // <?> Wildcard generic data type. Yet unknown type.
        studentRepository.save(student);
        // Geeft: http://localhost:8080/students/1 in Postman:  Headers > Location. Waar 1 is de id van de entry
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + student.getStudentNr()).toUriString());
//        return ResponseEntity.ok().build();
        return ResponseEntity.created(uri).body(student);
    }

    // Via de Repository query methods:
    // https://medium.com/@hashanlahiru6/all-about-query-methods-in-spring-data-jpa-52d74e5d2be7
    @GetMapping("/fullname") // localhost:8080/students/fullname?query=b
    public ResponseEntity<List<Student>> getStudentBySubstring(@RequestParam(name="query") String substring){
        List<Student> studentList = studentRepository.findByFullNameContainingIgnoreCase(substring);
        if(!studentList.isEmpty()){
            return  ResponseEntity.ok(studentList);
        } else {
            return ResponseEntity.notFound().build(); // Wat doet build()?
        }
    }






}
