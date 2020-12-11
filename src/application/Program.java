package application;

import db.*;
import model.dao.*;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

public class Program {

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

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("==================== TEST 1: Seller findById ====================");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("\n==================== TEST 2: Seller findByDepartment ====================");
        Department department = new Department(2, "test");
        List<Seller> list = sellerDao.findByDepartment(department);
        for (Seller obj : list){
            System.out.println(obj);
        }

        System.out.println("\n==================== TEST 3: Seller findByDepartment ====================");
        list = sellerDao.findAll();
        for (Seller obj : list){
            System.out.println(obj);
        }

//        System.out.println("\n==================== TEST 4: Seller insert ====================");
//        Seller sellerInsert = new Seller(null, "Thiago", "thiago@test.com", new Date(), 6000.0, department);
//        sellerDao.insert(sellerInsert);
//        System.out.println("Inserted! New id = " + sellerInsert.getId());

        System.out.println("\n==================== TEST 5: Seller update ====================");
        Seller sellerUpdate = new Seller();
        sellerUpdate = sellerDao.findById(7);
        sellerUpdate.setEmail("updateTest@test.com");
        sellerDao.update(sellerUpdate);
        System.out.println("Update successful");
    }

}
