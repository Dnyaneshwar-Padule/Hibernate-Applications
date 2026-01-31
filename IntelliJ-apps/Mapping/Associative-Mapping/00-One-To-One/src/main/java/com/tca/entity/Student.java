package com.tca.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    private Integer rno;

    @Column(length = 32, nullable = false)
    private String name;

    @Column(columnDefinition = "FLOAT CHECK(per >= 0 and per <= 100)")
    private Double per;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Certificate certificate;

    public Integer getRno() {
        return rno;
    }

    public String getName() {
        return name;
    }

    public Double getPer() {
        return per;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setRno(Integer rno) {
        this.rno = rno;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public void setPer(Double per) {
        this.per = per;
    }

    public void setName(String name) {
        this.name = name;
    }
}
