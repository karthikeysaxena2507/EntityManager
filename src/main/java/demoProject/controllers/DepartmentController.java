package demoProject.controllers;

import demoProject.models.Department;
import demoProject.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/departments")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GET
    @Path("/hello")
    public Response sayHello() {
        return Response
                .status(Response.Status.OK)
                .entity(departmentRepository.sayHello())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDepartments() {
        return Response
                .status(Response.Status.OK)
                .entity(departmentRepository.findAll(0, 10))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartmentById(@PathParam("id") Long departmentId) {
        return Response
                .status(Response.Status.OK)
                .entity(departmentRepository.findById(departmentId))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDepartment(Department department) {
        departmentRepository.create(department);
        return Response
                .status(Response.Status.OK)
                .entity("Department created Successfully")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDepartment(Department department) {
        return Response
                .status(Response.Status.OK)
                .entity(departmentRepository.save(department))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAllDepartment(List<Department> departments) {
        return Response
                .status(Response.Status.OK)
                .entity(departmentRepository.saveAll(departments))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDepartmentById(@PathParam("id") Long departmentId) {
        departmentRepository.deleteById(departmentId);
        return Response
                .status(Response.Status.OK)
                .entity("SUCCESS: DEPARTMENT DELETED")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @POST
    @Path("/delete")
    public Response deleteDepartment(Department department) {
        departmentRepository.delete(department);
        return Response
                .status(Response.Status.OK)
                .entity("SUCCESS: DEPARTMENT DELETED")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @POST
    @Path("/deleteAll")
    public Response deleteAllDepartments(List<Department> departments) {
        departmentRepository.deleteAll(departments);
        return Response
                .status(Response.Status.OK)
                .entity("Success")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
