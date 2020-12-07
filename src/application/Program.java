package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);
        System.out.println(seller.getDepartment().getName());

    }

}
