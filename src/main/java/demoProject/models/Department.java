package demoProject.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "departments")
@NamedQueries({ @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d") })
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "department_id", unique = true, nullable = false)
    private Long departmentId;

    @Column(name = "department_name", nullable = false)
    @NotNull
    private String departmentName;

    protected Department() {

    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
