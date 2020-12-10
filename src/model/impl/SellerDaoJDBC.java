package model.impl;

import db.*;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
               Department department = instantiateDepartments(resultSet);
               Seller seller = instantiateSeller(resultSet, department);
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

    private Seller instantiateSeller(ResultSet resultSet, Department department) throws SQLException {
        Seller seller = new Seller();
        seller.setId(resultSet.getInt("Id_Seller"));
        seller.setName(resultSet.getString("Name"));
        seller.setEmail(resultSet.getString("Email"));
        seller.setBirthDate(resultSet.getDate("BirthDate"));
        seller.setBaseSalary(resultSet.getDouble("BaseSalary"));
        seller.setDepartment(department);
        return seller;
    }

    private Department instantiateDepartments(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("DepartmentId"));
        department.setName(resultSet.getString("DepName"));
        return department;
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = connection.prepareStatement(
                    "select seller.*, department.Name as DepName from seller "
                            + "inner join department on seller.DepartmentId = department.Id_Department "
                            + "order by Name"
            );

            resultSet = preparedStatement.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            /*Check result*/
            while (resultSet.next()){
                Department dep = map.get(resultSet.getInt("DepartmentId"));

                /*check to return the same department if any*/
                if (dep == null){
                    dep = instantiateDepartments(resultSet);
                    map.put(resultSet.getInt("DepartmentId"), dep);
                }

                Seller seller = instantiateSeller(resultSet, dep);
                list.add(seller);
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = connection.prepareStatement(
                    "select seller.*, department.Name as DepName from seller "
                    + "inner join department on seller.DepartmentId = department.Id_Department "
                    + "where DepartmentId = ? "
                    + "order by Name"
                    );

            preparedStatement.setInt(1, department.getId());
            resultSet = preparedStatement.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            /*Check result*/
            while (resultSet.next()){
                Department dep = map.get(resultSet.getInt("DepartmentId"));

                /*check to return the same department if any*/
                if (dep == null){
                    dep = instantiateDepartments(resultSet);
                    map.put(resultSet.getInt("DepartmentId"), dep);
                }

                Seller seller = instantiateSeller(resultSet, dep);
                list.add(seller);
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(preparedStatement);
        }
    }
}
