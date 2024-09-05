package com.systemlab.domain.session;

import com.systemlab.domain.laboratory.Laboratory;
import com.systemlab.domain.supervisor.Supervisor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "session")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    @Id
    @Column(name = "id_session")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean status;

    @Enumerated(EnumType.STRING)
    private ShiftSession shift;

    @ManyToOne
    @JoinColumn(name = "id_laboratory")
    private Laboratory laboratory;

    @OneToOne
    @JoinColumn(name = "id_supervisor")
    private Supervisor supervisor;

    public Session(ShiftSession shift, Laboratory laboratory, Supervisor supervisor) {
        this.status = false;
        this.shift = shift;
        this.laboratory = laboratory;
        this.supervisor = supervisor;
    }

    
}
