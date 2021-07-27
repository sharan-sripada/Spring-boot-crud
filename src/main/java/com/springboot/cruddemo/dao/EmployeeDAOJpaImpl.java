package com.springboot.cruddemo.dao;

import com.springboot.cruddemo.entity.Employee;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
//@Primary
public class EmployeeDAOJpaImpl implements EmployeeDAO{
    private EntityManager entityManager;

    EmployeeDAOJpaImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }


    @Override
    public List<Employee> findAll() {

        Query query= entityManager.createQuery("from Employee");

        List<Employee> employeeList=query.getResultList();

        return employeeList;
    }

    @Override
    public Employee findById(int theId) {
        Employee employee=entityManager.find(Employee.class,theId);

        return employee;
    }

    @Override
    public void save(Employee theEmployee) {

        Employee employee=entityManager.merge(theEmployee);
        theEmployee.setId(employee.getId());
    }

    @Override
    public void deleteById(int theId) {

        Query query= entityManager.createQuery("delete from Employee where id=:employeeId");
        query.setParameter("employeeId",theId);
        query.executeUpdate();

    }
}
