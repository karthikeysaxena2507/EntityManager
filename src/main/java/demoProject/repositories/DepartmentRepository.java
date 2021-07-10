package demoProject.repositories;

import demoProject.models.Department;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface DepartmentRepository extends EntityRepository<Department,Long> {
    public String sayHello();
}
