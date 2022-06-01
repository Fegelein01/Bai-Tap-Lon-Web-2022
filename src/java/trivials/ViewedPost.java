/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trivials;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ViewedPost {

    private ArrayList<Integer> viewPostId;

    public ViewedPost() {
        viewPostId = new ArrayList<>();
    }

    public void addPost(int id) {
        viewPostId.add(id);
    }

    public boolean contain(int id) {
        if (viewPostId.contains(id)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.viewPostId.toString();
    }

}
