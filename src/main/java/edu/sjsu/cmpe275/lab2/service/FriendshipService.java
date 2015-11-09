package edu.sjsu.cmpe275.lab2.service;

import edu.sjsu.cmpe275.lab2.model.Friendship;

import java.util.List;

public interface FriendshipService {

    public void add(Friendship friendship);

    public void delete(Friendship friendship);

    public Friendship search(Integer id1, Integer id2);

    public List<Integer> getAllFriends(int id);
}
