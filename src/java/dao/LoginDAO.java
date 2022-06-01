/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.User;

public class LoginDAO extends DAO {
    
    public LoginDAO() {
        super();
    }
    
    public boolean checkLogin(User user) {
        boolean result = false;
        String sql = "SELECT name, role FROM tblUserTest WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setName(rs.getString("name"));
                user.setRole(rs.getString("role"));
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean register(User user) {
        String sql = "insert into tblusertest (username, password, name,role, email) value (?,?,?,?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getEmail());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean checkExisted(String type, String value) {
        String sql = "";
        if (type.equals("email")) {
            sql = "select exists (select  * from tblusertest where email = ?) as e";
        } else {
            sql = "select exists (select  * from tblusertest where username = ?) as e";
        }
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, value);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int e = rs.getInt("e");
                if (e == 1) {
                    return true;
                } else {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<User> getUsers() {
        ArrayList<User> result = new ArrayList<>();
        String sql = "Select * from tblusertest";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setName(rs.getString("name"));
                u.setRole(rs.getString("role"));
                u.setEmail(rs.getString("email"));
                result.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public User getUser(int id) {
        User result = new User();
        String sql = "Select * from tblusertest where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setName(rs.getString("name"));
                u.setRole(rs.getString("role"));
                u.setEmail(rs.getString("email"));
                result = u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public User getUserByUsername(String username) {
        User result = new User();
        String sql = "Select * from tblusertest where username = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setName(rs.getString("name"));
                u.setRole(rs.getString("role"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                result = u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public void delete(int id) {
        String sql = "delete from tblusertest where  id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void changeName(int id, String name) {
        String sql = "update tblUserTest set name = ? where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void changeEmail(int id, String email) {
        String sql = "update tblUserTest set email = ? where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void changeRole(int id, String role) {
        String sql = "update tblUserTest set role = ? where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, role);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
