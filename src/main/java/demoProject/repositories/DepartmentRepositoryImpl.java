package demoProject.repositories;

import demoProject.models.Department;
import org.springframework.stereotype.Service;

@Service("departmentRepositoryImpl")
public class DepartmentRepositoryImpl
        extends EntityRepositoryImpl<Department, Long>
        implements DepartmentRepository
{

    @Override
    public String getFindAllQueryName() {
        return "Department.findAll";
    }

    @Override
    public Class getEntityClass() {
        return Department.class;
    }

    @Override
    public String sayHello() {
        return "hello";
    }
}
