package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Program2 {
    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;

        try{
            connection = DB.getConnection();
            statement  = connection.createStatement();
            System.out.println("Connection Successful!");
        } catch (SQLException e){
            e.printStackTrace();
        }

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        /*System.out.println("==================== TEST 1: Department insert ====================");
        Department department = new Department(null, "Test");
        departmentDao.insert(department);*/
//
        /*System.out.println("==================== TEST 2: Department update ====================");
        Department departmentUpdate = new Department(5, "Technology");
        departmentDao.update(departmentUpdate);*/

        /*System.out.println("==================== TEST 3: Department delete ====================");
        int id = 5;
        departmentDao.deleteById(id);*/

        /*System.out.println("==================== TEST 4: Department findById ====================");
        int id = 4;
        System.out.println(departmentDao.findById(id));*/

        /*System.out.println("==================== TEST 5: Department findAll ====================");
        List<Department> departmentFindAll = departmentDao.findAll();
        for (Department department : departmentFindAll){
            System.out.println(department);
        }*/

    }
}
