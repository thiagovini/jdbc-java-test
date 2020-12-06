package model.dao;

import model.entities.Seller;
import java.util.List;

public interface SellerDao {

    void insert(Seller objeto);

    void update(Seller objeto);

    void daleteById(Integer id);

    Seller findById(Integer id);

    List<Seller> findAll();
}
