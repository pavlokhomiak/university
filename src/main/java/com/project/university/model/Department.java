package com.project.university.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "department")
@SQLDelete(sql = "UPDATE department SET deleted = true WHERE id = ?")
@Where(clause = "deleted=false")
public class Department extends BaseEntity {

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private DepartmentType type;

    @ManyToOne
    @JoinColumn(name="lector_id", nullable=false)
    private Lector head;

    @ManyToMany(mappedBy = "departments", fetch = FetchType.EAGER)
    private List<Lector> lectors;
}
