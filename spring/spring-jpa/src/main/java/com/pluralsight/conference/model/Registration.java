package com.pluralsight.conference.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "registrations")
@NamedQueries({
        @NamedQuery(name = Registration.REGISTRATION_REPORT, query = Registration.REGISTRATION_REPORT_JPQL)
})
public class Registration {

    // Domain needs to be specified: Registration.
    public static final String REGISTRATION_REPORT = "Registration.registrationReport";

    public static final String REGISTRATION_REPORT_JPQL = "select new com.pluralsight.conference.model.RegistrationReport(r.name, c.name, c.description) from Registration r, Course c where r.id = c.registration.id";

    @Id
    // GenerationType.Identitiy uses mysql's auto_increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    // Grab at one level and return (avoids recursive lookup loops) this needs a @JsonBackReference on the other object of the relationship
    @JsonManagedReference
    // The mapped by string value corresponds to the field name on Course.java
    // Fetch Type = Lazy means, that this field is populated if it is called (e.g.: via getCourses())
    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courses = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
