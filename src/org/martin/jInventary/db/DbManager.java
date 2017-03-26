/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.jInventary.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.martin.jInventary.model.Product;
import org.martin.jInventary.model.ProductType;
import org.martin.jInventary.model.ProductView;
import org.martin.jInventary.model.User;
import org.martin.jInventary.model.UserType;

/**
 *
 * @author martin
 */
public class DbManager {
    private DbConnection connection;

    public static enum ORDER_TYPE{
        NAME, TYPE, QUANTITY;
    }
    
    public DbManager() throws SQLException {
        connection = new DbConnection("dbInventario");
    }

    public boolean isRightLogin(String nick, String passw) throws SQLException{
        ResultSet res = connection.execSelect("select isRightLogin('"+nick+"', '"+passw+"')");
        res.next();
        boolean isRightLogin = res.getBoolean(1);
        res.close();
        return isRightLogin;
    }
    
    public void addUser(User user){
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("insert into user values(null, '")
                .append('\'').append(user.getIdType()).append('\'').append(',')
                .append('\'').append(user.getName()).append('\'').append(',')
                .append('\'').append(user.getNick()).append('\'').append(',')
                .append('\'').append(user.getEmail()).append('\'').append(',')
                .append("AES_ENCRYPT('powerx7','").append(user.getPassword())
                .append("'))");
        connection.execQuery(sbQuery.toString());
        sbQuery = null;
    }
    
    public LinkedList<User> getUsers() {
        LinkedList<User> listUsers = new LinkedList<>();
        try (ResultSet res = connection.execSelect("select * from user")) {
            User u;
            
            while (res.next()) {
                u = new User(res.getInt(1), res.getByte(2), res.getString(3),
                        res.getString(4), res.getString(5), res.getString(6));
                listUsers.add(u);
            }
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUsers;
    }
    
    public LinkedList<String> getUserNicks(){
         LinkedList<String> listNicks = new LinkedList<>();
        try (ResultSet res = connection.execSelect("select nick from user")) {
            while (res.next())
                listNicks.add(res.getString(1));
            
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listNicks;
    }
    
    public User getUserByNick(String user) throws SQLException{
        ResultSet select = connection.execSelect("select * from user where (nick = '"+user+"' or "
                + "email = '"+user+"') and enabled = 1 limit 1");
        User u = null;
        
        if (select.next())
            u = new User(select.getInt(1), select.getByte(2), select.getString(3), 
                    select.getString(4), select.getString(5), select.getString(6));
        select.close();
        return u;
    }
    
    public User getUserByNickAndPassw(String nick, String passw){
        User user = null;
        try (ResultSet select = connection.execSelect("select * from user where nick = '"+nick+"' "
                + "and password = AES_ENCRYPT('powerx7', '"+passw+"') limit 1")) {
            if (select.next()) {
                user = new User(select.getInt(1), select.getByte(2), select.getString(3),
                        select.getString(4), select.getString(5), select.getString(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public void updateUser(User edited){
        connection.execQuery("update user set idType = "+edited.getIdType()+", "
                + "name = '"+edited.getName()+"', nick = '"+edited.getNick()+"', "
                + "email = '"+edited.getEmail()+"', password = AES_ENCRYPT('powerx7', "
                + "'"+edited.getPassword()+"')");
    }
    
    public void addProduct(Product product){
        StringBuilder sbInsert = new StringBuilder();
        sbInsert.append("insert into product values(null,")
                .append('\'').append(product.getName()).append('\'').append(',')
                .append('\'').append(product.getIdType()).append('\'').append(',')
                .append('\'').append(product.getQuantity()).append('\'').append(',')
                .append('\'').append(product.getMinQuantity()).append('\'').append(',')
                .append(product.isEnabled() ? 1 : 0).append(')');
        connection.execQuery(sbInsert.toString());
        sbInsert = null;
    }
    
    public LinkedList<ProductView> getProducts() {
        LinkedList<ProductView> products = new LinkedList<>();
        try (ResultSet res = connection.execSelect("select * from productView")) {
            ProductView pv;
            
            while (res.next()) {
                pv = new ProductView(res.getInt(1), res.getString(2), res.getString(3), 
                        res.getInt(4), res.getInt(5));
                products.add(pv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }
    
    public LinkedList<ProductView> getProductsByOrder(ORDER_TYPE order) throws SQLException{
        LinkedList<ProductView> products = new LinkedList<>();
        String orderType = order == ORDER_TYPE.NAME ? 
                "name" : (order == ORDER_TYPE.QUANTITY ? "quantity" : "type");
        
        ResultSet res = connection.execSelect("select * from productView order by "+orderType+
                " asc");
        ProductView pv;
        
        while (res.next()) {            
            pv = new ProductView(res.getInt(1), res.getString(2), res.getString(3), 
                    res.getInt(4), res.getInt(5));
            products.add(pv);
        }
        res.close();
        return products;
    }
    
    public LinkedList<ProductView> getProductsByOrder(ORDER_TYPE order, boolean asc) {
        LinkedList<ProductView> products = new LinkedList<>();
        String orderType = order == ORDER_TYPE.NAME ? 
                "name" : (order == ORDER_TYPE.QUANTITY ? "quantity" : "type");
        
        try (ResultSet res = connection.execSelect("select * from productView order by "+orderType+
                ' '+(asc ? "asc" : "desc"))) {
            ProductView pv;
            
            while (res.next()) {
                pv = new ProductView(res.getInt(1), res.getString(2), res.getString(3),
                        res.getInt(4), res.getInt(5));
                products.add(pv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }
    
    
    
    public LinkedList<ProductView> getProductsByName(String name) {
        LinkedList<ProductView> products = new LinkedList<>();
        try (ResultSet res = connection.execSelect("select * from productView where name like '%"+name+"%'")) {
            ProductView pv;
            
            while (res.next()) {
                pv = new ProductView(res.getInt(1), res.getString(2), res.getString(3), 
                        res.getInt(4), res.getInt(5));
                products.add(pv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }
    
    public LinkedList<Product> getProductsBy(String name) {
        LinkedList<Product> products = new LinkedList<>();
        try (ResultSet res = connection.execSelect("select * from product where name like '%"+name+"%'")) {
            Product prod;
            
            while (res.next()) {
                prod = new Product(res.getInt(1), res.getString(2), res.getByte(3), 
                        res.getInt(4), res.getInt(5), res.getBoolean(6));
                products.add(prod);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }
    
    public Product getProductBy(int id) {
        Product product = null;
        try (ResultSet res = connection.execSelect("select * from product where id = "+id+" limit 1")) {
            if (res.next()) {
                product = new Product(res.getInt(1), res.getString(2), res.getByte(3), 
                        res.getInt(4), res.getInt(5),res.getBoolean(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }
    
    public Product getProductBy(String name) {
        Product product = null;
        try (ResultSet res = connection.execSelect("select * from product where name = '"+name+"' limit 1")) {
            
            if (res.next()) {
                product = new Product(res.getInt(1), res.getString(2), res.getByte(3), 
                        res.getInt(4), res.getInt(5), res.getBoolean(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }
    
    public LinkedList<String> getProductNamesBy(String name) {
        LinkedList<String> listProdNames = new LinkedList<>();
        try (ResultSet res = connection.execSelect("select * from product where name like '%"+name+"%'")) {
            while (res.next()) {
                listProdNames.add(res.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProdNames;
    }
    
    public LinkedList<ProductView> getProductsByType(int idType) throws SQLException{
        LinkedList<ProductView> products = new LinkedList<>();
        ResultSet res = connection.execSelect("select * from productView where product.idType = "+idType+" order by productView.type asc");
        ProductView pv;
        
        while (res.next()) {
            pv = new ProductView(res.getInt(1), res.getString(2), res.getString(3), 
                    res.getInt(4), res.getInt(5));
            products.add(pv);
        }
        res.close();
        return products;
    }
    
    public LinkedList<ProductView> getProductsByNameAndType(String name, int idType) throws SQLException{
        LinkedList<ProductView> products = new LinkedList<>();
        ResultSet res = connection.execSelect("select * from productView where "
                + "name like '%"+name+"%'and product.idType = "+idType+" order by "
                + "productView.type asc");
        ProductView pv;
        
        while (res.next()) {
            pv = new ProductView(res.getInt(1), res.getString(2), res.getString(3), 
                    res.getInt(4), res.getInt(5));
            products.add(pv);
        }
        res.close();
        return products;
    }
    
    /*public Product getProductById(int id){
        Product prod = null;
        try (ResultSet res = connection.execSelect("select * from product where id = "+id+" limit 1")) {
            if (res.next()) {
                prod = new Product(res.getInt(1), res.getString(2), res.getByte(3), 
                        res.getInt(4), res.getBoolean(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prod;
    }*/
    
    public boolean receiveProduct(int idProduct, int quantity){
        if (getProductBy(idProduct) == null)
            return false;
        else{
            connection.execQuery("update product set quantity = quantity+"+quantity+" where id "
                + "= "+idProduct);
            return true;
        }
    }
    
    public boolean receiveProduct(String name, int quantity){
        if (getProductsByName(name).isEmpty())
            return false;
        
        else{
            connection.execQuery("update product set quantity = quantity+"+quantity+" where name "
                    + "= '"+name+'\'');
            return true;
        }
    }
    
    public boolean consumeProduct(int idProduct, int quantity){
        boolean isConsumed = false;
        int quantityProd = getProductQuantityById(idProduct);
        if (quantityProd >= quantity){
            isConsumed = true;
            connection.execQuery("update product set quantity = quantity-"+quantity+" "
                    + "where id = "+idProduct);
        }
        return isConsumed;
    }
    
    public boolean consumeProduct(String name, int quantity){
        boolean isConsumed = false;
        int quantityProd = getProductQuantityByName(name);
        if (quantityProd >= quantity){
            isConsumed = true;
            connection.execQuery("update product set quantity = quantity-"+quantity+" "
                    + "where name = '"+name+'\'');
        }
        return isConsumed;
    }
    
    public int getProductQuantityById(int id){
        int quantity = 0;
        try (ResultSet res = connection.execSelect("select quantity from product where id = "+id)) {
            if (res.next())
                quantity = res.getInt(1);
            
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quantity;
    }
    
    public int getProductQuantityByName(String name){
        int quantity = 0;
        try (ResultSet res = connection.execSelect("select quantity from product where name = '"+name+
                "' limit 1")) {
            if (res.next())
                quantity = res.getInt(1);
            
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quantity;
    }
    
    public void setQuantity(int idProduct, int newQuantity){
        if (newQuantity >= 0) {
            connection.execQuery("update product set quantity = "+newQuantity
                    + " where id = "+idProduct);
        }
    }
    
    public void deleteProductById(int id){
        connection.execQuery("delete from product where id = "+id);
    }
    
    public void enableProductById(int id){
        connection.execQuery("update product set enable = 1 where id = "+id);
    }
    
    public void disableProductById(int id){
        connection.execQuery("update product set enable = 0 where id = "+id);
    }
    
    public void updateProduct(Product newProd){
        String query = "update product set name = '"+newProd.getName()+"', "
                + "idType = "+newProd.getIdType()+", quantity = "+newProd.getQuantity()+", "
                + " minQuantity = "+ newProd.getMinQuantity() +" where id = "+newProd.getId();
        connection.execQuery(query);
    }
    
    public LinkedList<ProductView> getUrgentProducts(){
        LinkedList<ProductView> listProductsView = new LinkedList<>();
        try {
            ResultSet res = connection.execSelect("select * from productView where "
                    + "quantity < minQuantity order by (minQuantity - quantity) desc");
            ProductView pv;
            
            while (res.next()) {
                pv = new ProductView(res.getInt(1), res.getString(2), res.getString(3),
                        res.getInt(4), res.getInt(5));
                listProductsView.add(pv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProductsView;
    }
    
    public void addType(ProductType type){
        addType(type.getName());
    }
    
    public void addType(String name){
        connection.execQuery("insert into productType values (null, '"+name+"', 1)");
    }
    
    public LinkedList<ProductType> getAllTypes(){
        LinkedList<ProductType> listTypes = new LinkedList<>();
        try (ResultSet res = connection.execSelect("select * from productType "
                + "where id != 7 order by name asc")) {
            
            while (res.next())
                listTypes.add(new ProductType(res.getByte(1), res.getString(2), res.getBoolean(3)));
            
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTypes;
    }
    
    public ProductType getType(int id){
        ProductType type = null;
        try (ResultSet res = connection.execSelect("select * from productType where id = "+id)) {
            
            if (res.next()) 
                type = new ProductType(res.getByte(1), res.getString(2), res.getBoolean(3));
            
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return type;
    }
    
    public ProductType getTypeByName(String name){
        ProductType type = null;
        try (ResultSet res = connection.execSelect("select * from productType where "
                + "name = '"+name+'\'')) {
            
            if (res.next()) 
                type = new ProductType(res.getByte(1), res.getString(2), res.getBoolean(3));
            
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return type;
    }
    
    public void updateType(ProductType pt){
        connection.execQuery("update productType set name = '"+pt.getName()+"' where id = "
                + pt.getId());
    }
    
    public void enableType(int id){
        connection.execQuery("update productType set enabled = 1 where id = "+id);
    }
    
    public void disableType(int id){
        connection.execQuery("update productType set enabled = 0 where id = "+id);
    }
    
    public void deleteType(int id){
        connection.execQuery("update product set idType = 7 where idType = "+id);
        connection.execQuery("delete from productType where id = "+id);
    }
    
    public void addUserType(UserType type){
        connection.execQuery("insert into userType values(null, '"+type.getName()+"')");
    }
    
    public LinkedList<UserType> getUserTypes(){
        LinkedList<UserType> listTypes = new LinkedList<>();
        try (ResultSet res = connection.execSelect("select * from userType")) {
            while (res.next())
                listTypes.add(new UserType(res.getByte(1), res.getString(2)));
        
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTypes;
    }
    
    public UserType getUserTypeBy(byte id){
        UserType ut = null;
        try (ResultSet res = connection.execSelect("select name from userType where id = "+id)) {
            if (res.next())
                ut = new UserType(id, res.getString(1));
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ut;
    }
    
    public void deleteUserTypeBy(byte id){
        connection.execQuery("delete from userType where id = "+id);
    }
    
    public void updateUserType(UserType ut){
        connection.execQuery("update userType set name = "+ut.getName()+" where id = "+ut.getId());
    }
    
}
