package model.dao;

import model.entities.Department;

import java.util.List;

public interface DepartmentDao {

    void insert(Department objeto);

    void update(Department objeto);

    void daleteById(Integer id);

    Department findById(Integer id);

    List<Department> findAll();
}
