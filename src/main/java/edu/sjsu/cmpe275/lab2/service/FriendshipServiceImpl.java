package edu.sjsu.cmpe275.lab2.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import edu.sjsu.cmpe275.lab2.dao.FriendshipDAO;

import java.util.List;

public class FriendshipServiceImpl implements FriendshipService{

    @Autowired
    private FriendshipDAO friendDao;

    @Transactional
    public void add(Frienship friend) {
        friendDao.add(friend);
    }

    @Transactional
    public void delete(Frienship friend) {
        friendDao.delete(friend);
    }

    @Transactional
    public Frienship search(Integer id1, Integer id2) {
        return friendDao.search(id1, id2);
    }

    @Transactional
    public List<Integer> getAllFriends(int personId) {
        return friendDao.getAllFriends(personId);
    }


}
