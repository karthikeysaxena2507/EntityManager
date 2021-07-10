package demoProject.repositories;

import demoProject.models.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestEntityManager
public class DepartmentRepositoryTest
        extends AbstractTransactionalTestNGSpringContextTests
{

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DepartmentRepository departmentRepository;

    private Department department;

    @Test
    @Transactional
    public void testFindAllDepartment() {
        Department department = entityManager.persist(new Department("a"));
        List<Department> departments = departmentRepository.findAll(0, 10);
        Assert.assertEquals(1, departments.size());
    }

    @Test
    @Transactional
    public void testFindById() {
        entityManager.persist(new Department("a"));
        Department actualDepartment = entityManager.find(Department.class, 1L);
        Assert.assertEquals("a", actualDepartment.getDepartmentName());
    }

    @Test
    @Transactional
    public void testDeleteById() {
        entityManager.persist(new Department("a"));
        departmentRepository.deleteById(1L);
        Assert.assertTrue(departmentRepository.findAll(0, 10).isEmpty());
    }

    @Test
    @Transactional
    public void testDeleteAll() {
        List<Department> departments = new ArrayList<>();
        Department department1 = new Department("a");
        Department department2 = new Department("b");
        departments.add(department1);
        departments.add(department2);
        departmentRepository.deleteAll(departments);
        Assert.assertTrue(departmentRepository.findAll(0, 10).isEmpty());
    }

    @Test
    @Transactional
    public void testDelete() {
        department = new Department("a");
        entityManager.persist(department);
        departmentRepository.delete(department);
        Assert.assertTrue(departmentRepository.findAll(0, 10).isEmpty());
    }

    @Test
    @Transactional
    public void testCreate() {
        department = new Department("a");
        departmentRepository.create(department);
        Assert.assertEquals(1, departmentRepository.findAll(0, 10).size());
    }

    @Test
    @Transactional
    public void testUpdate() {
        department = new Department("a");
        department = entityManager.persist(department);
        department.setDepartmentName("b");
        departmentRepository.save(department);
        Assert.assertEquals("b", departmentRepository.findById(1L).getDepartmentName());
    }

    @Test
    @Transactional
    public void testSaveAll() {
        List<Department> departments = new ArrayList<>();
        Department department1 = new Department("a");
        Department department2 = new Department("b");
        departments.add(department1);
        departments.add(department2);
        departmentRepository.saveAll(departments);
        Assert.assertEquals(2, departmentRepository.findAll(0, 10).size());
    }

}
