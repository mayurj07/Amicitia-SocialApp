package edu.sjsu.cmpe275.lab2.service;


import edu.sjsu.cmpe275.lab2.model.Friendship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.sjsu.cmpe275.lab2.dao.FriendshipDAO;

import java.util.List;

@Service("friendshipService")
@Transactional
public class FriendshipServiceImpl implements FriendshipService {

    @Autowired
    private FriendshipDAO friendDao;

    public void add(Friendship friend) {
        friendDao.add(friend);
    }

    public void delete(Friendship friend) {
        friendDao.delete(friend);
    }

    public Friendship checkIfFriends(Integer id1, Integer id2) {
        return friendDao.search(id1, id2);
    }

    public List<Integer> getAllFriends(int personId) {
        return friendDao.getAllFriends(personId);
    }

}
