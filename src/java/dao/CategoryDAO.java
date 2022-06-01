/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Category;

/**
 *
 * @author Admin
 */
public class CategoryDAO extends DAO {

    public ArrayList<Category> getCategories() {
        String sql = "Select * from tblcategory";
        ArrayList<Category> result = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setNote(rs.getString("note"));
                c.setParentId(rs.getInt("parentId"));
                result.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Category> getBlogCategories(int blogId) {
        ArrayList<Category> result = new ArrayList<>();
        String sql = "select DISTINCT tblcategory.id, tblcategory.name, tblcategory.note, tblcategory.parentid \n"
                + "from tblpostcategory\n"
                + "inner join tblcategory\n"
                + "on tblcategory.id = tblpostcategory.categoryid\n"
                + "where tblpostcategory.postid = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, blogId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setNote(rs.getString("note"));
                c.setParentId(rs.getInt("parentId"));
                result.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Integer> getBlogCategoryId(int blogId) {
        ArrayList<Integer> result = new ArrayList<>();
        String sql = "select DISTINCT tblcategory.id, tblcategory.name, tblcategory.note, tblcategory.parentid \n"
                + "from tblpostcategory\n"
                + "inner join tblcategory\n"
                + "on tblcategory.id = tblpostcategory.categoryid\n"
                + "where tblpostcategory.postid = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, blogId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getInt("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }

    public Category getCategory(int id) {
        String sql = "Select * from tblcategory where id = ?";
        Category result = new Category();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setNote(rs.getString("note"));
                c.setParentId(rs.getInt("parentId"));
                result = c;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void editCategory(int id, String name, String note) {
        String sql = "update tblcategory set name = ?, note = ? where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(3, id);
            ps.setString(2, note);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean add(String name, String note) {
        String sql = "insert into tblcategory (name,note) value (?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, note);
            ps.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean delete(int id) {
        String sql = "delete from tblcategory where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
