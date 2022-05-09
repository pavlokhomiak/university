package com.project.university.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.List;

@Data
@Entity
@Table(name = "lector")
@Accessors(chain = true)
@SQLDelete(sql = "UPDATE lector SET deleted = true WHERE id = ?")
@Where(clause = "deleted=false")
public class Lector extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "salary")
    private BigInteger salary;

    @Column(name = "degree")
    @Enumerated(EnumType.STRING)
    private Degree degree;

    @ManyToMany
    @JoinTable(
        name = "department_lector",
        joinColumns = @JoinColumn(name = "lector_id"),
        inverseJoinColumns = @JoinColumn(name = "department_id"))
    private List<Department> departments;

}
