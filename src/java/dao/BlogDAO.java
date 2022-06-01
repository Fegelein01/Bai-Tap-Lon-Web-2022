/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import model.Blog;
import model.Comment;
import model.User;

/**
 *
 * @author Admin
 */
public class BlogDAO extends DAO {

    public BlogDAO() {
        super();
    }

    public ArrayList<Blog> getBlogs() {
        ArrayList<Blog> result = new ArrayList<>();
        String sql = "SELECT * FROM tblposttest order by postDate desc";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog b = new Blog();
                b.setAuthor(rs.getString("author"));
                b.setContent(rs.getString("content"));
                b.setId(rs.getInt("id"));
                b.setUsername(rs.getString("username"));
                b.setTitle(rs.getString("title"));
                b.setPostDate(rs.getDate("postdate").toLocalDate());
                b.setEditDate(rs.getDate("lastEdit").toLocalDate());
                b.setLike(rs.getInt("up"));
                b.setDislike(rs.getInt("down"));
                b.setView(rs.getInt("view"));
                result.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Blog> getBlogsOrderBy(String s) {
        ArrayList<Blog> result = new ArrayList<>();
        String sql = "SELECT * FROM tblposttest order by ";
        switch (s) {
            case "title":
                sql = sql + "title";
                break;
            case "Author":
                sql = sql + "author";
                break;
            case "post date":
                sql = sql + "postdate desc";
                break;
            case "last edit":
                sql = sql + "lastedit desc";
                break;
            case "view":
                sql = sql + "view desc";
                break;
            case "like":
                sql = sql + "up desc";
                break;
            case "dislike":
                sql = sql + "down desc";
                break;
        }
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog b = new Blog();
                b.setAuthor(rs.getString("author"));
                b.setContent(rs.getString("content"));
                b.setId(rs.getInt("id"));
                b.setUsername(rs.getString("username"));
                b.setTitle(rs.getString("title"));
                b.setPostDate(rs.getDate("postdate").toLocalDate());
                b.setEditDate(rs.getDate("lastEdit").toLocalDate());
                b.setLike(rs.getInt("up"));
                b.setDislike(rs.getInt("down"));
                b.setView(rs.getInt("view"));
                result.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Blog> getBlogsPage(int page) {
        ArrayList<Blog> result = new ArrayList<>();
        String sql = "SELECT * FROM tblposttest order by postDate desc limit ? ,?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, 10 * (page - 1));
            ps.setInt(2, 10 * page);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog b = new Blog();
                b.setAuthor(rs.getString("author"));
                b.setContent(rs.getString("content"));
                b.setId(rs.getInt("id"));
                b.setUsername(rs.getString("username"));
                b.setTitle(rs.getString("title"));
                b.setPostDate(rs.getDate("postdate").toLocalDate());
                b.setEditDate(rs.getDate("lastEdit").toLocalDate());
                b.setLike(rs.getInt("up"));
                b.setDislike(rs.getInt("down"));
                result.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Blog> getBlogsByTitle(String name, int page) {
        ArrayList<Blog> result = new ArrayList<>();
        String sql = "SELECT * FROM tblposttest where title like ? order by postDate desc limit ? ,?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ps.setInt(2, 10 * (page - 1));
            ps.setInt(3, 10 * page);
            ResultSet rs = ps.executeQuery();
            System.out.println(ps);
            while (rs.next()) {
                Blog b = new Blog();
                b.setAuthor(rs.getString("author"));
                b.setContent(rs.getString("content"));
                b.setId(rs.getInt("id"));
                b.setUsername(rs.getString("username"));
                b.setTitle(rs.getString("title"));
                b.setPostDate(rs.getDate("postdate").toLocalDate());
                b.setEditDate(rs.getDate("lastEdit").toLocalDate());
                b.setLike(rs.getInt("up"));
                b.setDislike(rs.getInt("down"));
                result.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Blog getBlog(int id) {
        Blog result = new Blog();
        String sql = "SELECT * FROM tblposttest WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Blog b = new Blog();
                b.setAuthor(rs.getString("author"));
                b.setContent(rs.getString("content"));
                b.setId(rs.getInt("id"));
                b.setUsername(rs.getString("username"));
                b.setTitle(rs.getString("title"));
                b.setPostDate(rs.getDate("postdate").toLocalDate());
                b.setEditDate(rs.getDate("lastEdit").toLocalDate());
                b.setLike(rs.getInt("up"));
                b.setDislike(rs.getInt("down"));
                result = b;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void update(Blog b) {
        String sql = "Update tblposttest set content = ?, title =?, lastEdit=?  WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, b.getContent());
            ps.setString(2, b.getTitle());
            ps.setDate(3, Date.valueOf(b.getEditDate()));
            ps.setInt(4, b.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        sql = "Delete from tblpostcategory where postid = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, b.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<Integer> categoriesId = b.getCategories();
        for (Integer ci : categoriesId) {
            sql = "Insert into tblpostcategory value (?, ?)";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, b.getId());
                ps.setInt(2, ci);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(Blog b) {
        ArrayList<String> sql = new ArrayList<>();
        sql.add("Delete from tblcommenttest where postid =?");
        sql.add("Delete from tbllike where postid =?");
        sql.add("Delete from tbldislike where postid =?");
        sql.add("Delete from tblpostcategory where postid =?");
        sql.add("Delete from tblposttest where id =?");
        for (int i = 0; i < sql.size(); i++) {
            try {
                PreparedStatement ps = con.prepareStatement(sql.get(i));
                ps.setInt(1, b.getId());
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void create(Blog b) {
        String sql = "Insert into tblposttest (author, content, username, postDate, lastEdit, view, title) "
                + "values ( ? , ?, ?,?,?,?,? )";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, b.getAuthor());
            ps.setString(2, b.getContent());
            ps.setString(3, b.getUsername());
            ps.setDate(4, Date.valueOf(b.getPostDate()));
            ps.setDate(5, Date.valueOf(b.getEditDate()));
            ps.setInt(6, 0);
            ps.setString(7, b.getTitle());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sql = "SELECT id FROM tblposttest ORDER BY id DESC LIMIT 1"; //Lấy ra row vừa tạo (dòng cuối)
        int id = -1;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Integer> categoriesId = b.getCategories();
        for (Integer ci : categoriesId) {
            sql = "Insert into tblpostcategory value (?, ?)";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ps.setInt(2, ci);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Blog> getBlogsByCategory(int id, int page) {
        ArrayList<Blog> result = new ArrayList<>();
        String sql = "select tbl1.id, tbl1.author, tbl1.content, tbl1.username, tbl1.postDate,\n"
                + "tbl1.lastEdit, tbl1.view, tbl1.title, tbl1.categoryid,\n"
                + "tblcategory.name, tblcategory.note\n"
                + "from (select tblposttest.id, tblposttest.author, \n"
                + "tblposttest.content, tblposttest.username,\n"
                + "tblposttest.postDate, tblposttest.lastEdit, \n"
                + "tblposttest.view, tblposttest.title,\n"
                + "tblpostcategory.categoryid-- , tblcategory.name, tblcategory.note\n"
                + "from tblposttest\n"
                + "inner join tblpostcategory\n"
                + "on tblposttest.id = tblpostcategory.postid) as tbl1\n"
                + "inner join tblcategory\n"
                + "on tbl1.categoryid = tblcategory.id\n"
                + "where tblcategory.id in(?) limit ? ,?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, 10 * (page - 1));
            ps.setInt(3, 10 * page);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog b = new Blog();
                b.setAuthor(rs.getString("author"));
                b.setContent(rs.getString("content"));
                b.setId(rs.getInt("id"));
                b.setUsername(rs.getString("username"));
                b.setTitle(rs.getString("title"));
                b.setPostDate(rs.getDate("postdate").toLocalDate());
                b.setEditDate(rs.getDate("lastEdit").toLocalDate());
                result.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void increaseView(int PostId) {
        String sql = "update tblposttest\n"
                + "set view = view +1\n"
                + "where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, PostId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void likePost(int postid, String username, boolean check) { //CHeck là biến xét xem đang check hoặc like thật, 
//dùng trong trường hợp đã like => click dislike => like sẽ tụt và dislike tăng
        String sql = "select count(username) as c from tbllike where username = ? and postid = ?";
        int result = -1;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setInt(2, postid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt("c");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result == 0) { // KHi chưa like thì tăng like
            sql = "Update tblposttest set up = up + 1 where id = ?";
            if (check == true) {
                result = -1;
                try {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, postid);
                    ps.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                result = -3;
            }
        } else { //Đã like rồi thì click vào thành ko like
            sql = "Update tblposttest set up = up - 1 where id = ?";
            result = -2;
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, postid);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (result == -2) { //Người dùng ko còn like post đó nữa
            sql = "delete from tbllike where username = ? and postid = ?";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setInt(2, postid);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (result == -1) { //Người dùng like post đó
            sql = "insert into tbllike value(?,?)";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setInt(2, postid);
                System.out.println(ps);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dislikePost(int postid, String username, boolean check) {  //Tương tự với like
        String sql = "select count(username) as c from tbldislike where username = ? and postid = ?";
        int result = -1;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setInt(2, postid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt("c");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result == 0) {
            sql = "Update tblposttest set down = down + 1 where id = ?";
            if (check == true) {
                result = -1;
                try {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, postid);
                    ps.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                result = -3;
            }
        } else {
            sql = "Update tblposttest set down = down - 1 where id = ?";
            result = -2;
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, postid);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (result == -2) {
            sql = "delete from tbldislike where username = ? and postid = ?";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setInt(2, postid);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (result == -1) {
            sql = "insert into tbldislike value(?,?)";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setInt(2, postid);
                System.out.println(ps);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int count() {
        int result = 0;
        String sql = "SELECT count(id) as c FROM tblposttest";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result = rs.getInt("c");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int countWithCategory(int id) {
        int result = 0;
        String sql = "select count(tbl2.ac) as c from (select tbl1.id as ac, tbl1.author, tbl1.content, tbl1.username, tbl1.postDate,\n"
                + "                tbl1.lastEdit, tbl1.view, tbl1.title, tbl1.categoryid,\n"
                + "                tblcategory.name, tblcategory.note\n"
                + "                from (select tblposttest.id, tblposttest.author, \n"
                + "                tblposttest.content, tblposttest.username,\n"
                + "                tblposttest.postDate, tblposttest.lastEdit, \n"
                + "                tblposttest.view, tblposttest.title,\n"
                + "                tblpostcategory.categoryid-- , tblcategory.name, tblcategory.note\n"
                + "                from tblposttest\n"
                + "                inner join tblpostcategory\n"
                + "                on tblposttest.id = tblpostcategory.postid) as tbl1\n"
                + "                inner join tblcategory\n"
                + "                on tbl1.categoryid = tblcategory.id\n"
                + "                where tblcategory.id in(?)) as tbl2";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result = rs.getInt("c");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int countWithSearch(String name) {
        int result = 0;
        String sql = "SELECT count(id) as c FROM tblposttest where title like ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt("c");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
