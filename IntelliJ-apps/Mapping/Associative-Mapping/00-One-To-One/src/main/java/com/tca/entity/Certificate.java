package com.tca.entity;

import jakarta.persistence.*;

@Entity
@Table(name="CERTIFICATE")
public class Certificate {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cno;

    @Column(length = 32, nullable = false)
    private String title;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_RNO", unique = true)
    private Student student;

    public void setCno(Integer cno) {
        this.cno = cno;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getCno() {
        return cno;
    }

    public Student getStudent() {
        return student;
    }

    public String getTitle() {
        return title;
    }
}
