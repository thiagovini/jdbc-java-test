package model.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection connection;

    public SellerDaoJDBC(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(Seller objeto) {

    }

    @Override
    public void update(Seller objeto) {

    }

    @Override
    public void daleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = connection.prepareStatement(
                    "select seller.*, department.Name as DepName from seller "
                    + "inner join department on seller.DepartmentId = department.Id_Department "
                    + "where seller.Id_Seller = ?");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            /*Check result*/
            if(resultSet.next()){
                Department department = new Department();
                department.setId(resultSet.getInt("DepartmentId"));
                department.setName(resultSet.getString("DepName"));
                Seller seller = new Seller();
                seller.setId(resultSet.getInt("Id_Seller"));
                seller.setName(resultSet.getString("Name"));
                seller.setEmail(resultSet.getString("Email"));
                seller.setBirthDate(resultSet.getDate("BirthDate"));
                seller.setBaseSalary(resultSet.getDouble("BaseSalary"));
                seller.setDepartment(department);
                return seller;
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }
}
