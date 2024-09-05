package com.systemlab.domain.supervisor;

import com.systemlab.domain.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "supervisor")
@PrimaryKeyJoinColumn(name = "id_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supervisor extends User {
    @Id
    @Column(name = "id_supervisor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String registration;
    private String name;
    private String course;
    private String entryperiod;

    public Supervisor(String username, String password, String registration, String name, String course,
            String entryperiod) {
        super(username, password);
        this.registration = registration;
        this.name = name;
        this.course = course;
        this.entryperiod = entryperiod;
    }
}
