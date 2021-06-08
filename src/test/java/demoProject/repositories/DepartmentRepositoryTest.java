package demoProject.repositories;

import demoProject.models.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestEntityManager
public class DepartmentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DepartmentRepository departmentRepository;

    private Department department;

    @Test
    @DisplayName("test find all department")
    @Transactional
    public void testFindAllDepartment() {
        entityManager.persist(new Department("a"));
        List<Department> departments = departmentRepository.findAll(0, 10);
        Assertions.assertEquals(1, departments.size());
    }

    @Test
    @DisplayName("test find By Id")
    @Transactional
    public void testFindById() {
        entityManager.persist(new Department("a"));
        Department actualDepartment = entityManager.find(Department.class, 1L);
        Assertions.assertEquals("a", actualDepartment.getDepartmentName());
    }

    @Test
    @DisplayName("test delete By Id")
    @Transactional
    public void testDeleteById() {
        entityManager.persist(new Department("a"));
        departmentRepository.deleteById(1L);
        Assertions.assertTrue(departmentRepository.findAll(0, 10).isEmpty());
    }

    @Test
    @DisplayName("test delete All")
    @Transactional
    public void testDeleteAll() {
        List<Department> departments = new ArrayList<>();
        Department department1 = new Department("a");
        Department department2 = new Department("b");
        departments.add(department1);
        departments.add(department2);
        departmentRepository.deleteAll(departments);
        Assertions.assertTrue(departmentRepository.findAll(0, 10).isEmpty());
    }

    @Test
    @DisplayName("test delete")
    @Transactional
    public void testDelete() {
        department = new Department("a");
        entityManager.persist(department);
        departmentRepository.delete(department);
        Assertions.assertTrue(departmentRepository.findAll(0, 10).isEmpty());
    }

    @Test
    @DisplayName("test create")
    @Transactional
    public void testCreate() {
        department = new Department("a");
        departmentRepository.create(department);
        Assertions.assertEquals(1, departmentRepository.findAll(0, 10).size());
    }

    @Test
    @DisplayName("test update")
    @Transactional
    public void testUpdate() {
        department = new Department("a");
        department = entityManager.persist(department);
        department.setDepartmentName("b");
        departmentRepository.save(department);
        Assertions.assertEquals("b", departmentRepository.findById(1L).getDepartmentName());
    }

    @Test
    @DisplayName("test save all")
    @Transactional
    public void testSaveAll() {
        List<Department> departments = new ArrayList<>();
        Department department1 = new Department("a");
        Department department2 = new Department("b");
        departments.add(department1);
        departments.add(department2);
        departmentRepository.saveAll(departments);
        Assertions.assertEquals(2, departmentRepository.findAll(0, 10).size());
    }

}
