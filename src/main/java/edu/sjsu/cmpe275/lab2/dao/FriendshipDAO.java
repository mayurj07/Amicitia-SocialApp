package edu.sjsu.cmpe275.lab2.dao;

import java.util.List;

import edu.sjsu.cmpe275.lab2.model.Friendship;

public interface FriendshipDAO {

    public void add(Friendship friendship);

    public void delete(Friendship friendship);

    public Friendship search(Integer id1, Integer id2);

    public List<Integer> getAllFriends(int id);
}
