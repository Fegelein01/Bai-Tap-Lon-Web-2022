/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.DAO.con;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Comment;

/**
 *
 * @author Admin
 */
public class CommentDAO {

//    public ArrayList<Comment> getSubComments(int postId, int parentId) { //id là id của post
//        ArrayList<Comment> result = new ArrayList<>();
//        String sql = "Select * from tblcommenttest where postid = ? and parentId = ?";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, postId);
//            ps.setInt(2, parentId);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Comment c = new Comment();
//                c.setId(rs.getInt("id"));
//                c.setAuthor(rs.getString("author"));
//                c.setContent(rs.getString("content"));
//                c.setParentId(rs.getInt("parentId"));
//                c.setPostId(rs.getInt("PostId"));
//                result.add(c);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

    public ArrayList<Comment> getComments(int postId) { //id là id của post
        ArrayList<Comment> result = new ArrayList<>();
        String sql = "Select * from tblcommenttest where postid = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comment c = new Comment();
                c.setId(rs.getInt("id"));
                c.setUsername(rs.getString("username"));
                c.setAuthor(rs.getString("author"));
                c.setContent(rs.getString("content"));
                c.setParentId(rs.getInt("parentId"));
                c.setPostId(rs.getInt("PostId"));
                c.setPostDate(rs.getDate("postdate").toLocalDate());
                c.setState(rs.getString("state"));
                result.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Comment getComment(int id) { //id là id của post
        Comment result = new Comment();
        String sql = "Select * from tblcommenttest where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Comment c = new Comment();
                c.setId(rs.getInt("id"));
                c.setAuthor(rs.getString("author"));
                c.setUsername(rs.getString("username"));
                c.setContent(rs.getString("content"));
                c.setParentId(-1);
                c.setPostId(rs.getInt("PostId"));
                c.setPostDate(rs.getDate("postdate").toLocalDate());
                c.setState(rs.getString("state"));
                result = c;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void delete(Comment c) {
        int id = c.getId();
        int parentId = c.getId();
        if (parentId != -1) {
            String sql = "delete from tblcommenttest where parentId = ?";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, parentId);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String sql = "delete from tblcommenttest where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addComment(Comment c) {
        String sql = "Insert into tblcommenttest (author, content, postId, postdate, parentId, username) "
                + "values ( ? , ?, ? ,?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getAuthor());
            ps.setString(2, c.getContent());
            ps.setInt(3, c.getPostId());
            ps.setDate(4, Date.valueOf(c.getPostDate()));
            ps.setInt(5, c.getParentId());
            ps.setString(6, c.getUsername());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void addSubComment(Comment c) {
//        String sql = "Insert into tblcommenttest (author, content, postId, parentId) "
//                + "values ( ? , ?, ?, ? )";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, c.getAuthor());
//            ps.setString(2, c.getContent());
//            ps.setInt(3, c.getPostId());
//            ps.setInt(4, c.getParentId());
//            ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public void edit(int id, String content) {
        String sql = "update tblcommenttest set content = ?, state = '(edited)' where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(2, id);
            ps.setString(1, content);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
