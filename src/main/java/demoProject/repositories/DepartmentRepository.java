package demoProject.repositories;

import demoProject.models.Department;
import org.springframework.stereotype.Component;

public interface DepartmentRepository extends EntityRepository<Department,Long> {
    public String sayHello();
}
