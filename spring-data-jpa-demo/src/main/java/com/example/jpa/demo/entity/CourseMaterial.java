package com.example.jpa.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = "course")
public class CourseMaterial {

    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;
    private String url;


    //CascadeType.ALL - When save CourseMaterial data, It depends on Course data. So Course data also stored.
    //fetch = FetchType.EAGER - Load entire data (Ex: CourseMaterial Object with inner object (Course Object))
    //fetch = FetchType.LAZY - Load only needed data (Ex: Load only CourseMaterial Object)
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "courseId"
    )
    private Course course;

}
