package edu.sjsu.cmpe275.lab2.dao;


import edu.sjsu.cmpe275.lab2.model.Friendship;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("friendDao")
public class FriendshipDAOImpl implements FriendshipDAO {

    @Autowired
    private SessionFactory session;

    public void add(Friendship friend) {
        session.getCurrentSession().save(friend);
    }

    public void delete(Friendship friend) {
        Query query = session.getCurrentSession().createQuery("delete Friendship where id = :ID");
        query.setParameter("ID", friend.getId());
        query.executeUpdate();
    }

    public Friendship search(Integer id1, Integer id2) {
        Criteria c = session.getCurrentSession().createCriteria(Friendship.class, "o");
        c.add(Restrictions.eq("o.person1.id", id1));
        c.add(Restrictions.eq("o.person2.id", id2));
        List<Friendship> listFriends = c.list();
        if(listFriends!=null && listFriends.size()>0){
            return listFriends.get(0);
        }
        return null;
    }

    public List<Integer> getAllFriends(int personId) {
        Criteria c = session.getCurrentSession().createCriteria(Friendship.class, "f");
        c.add(Restrictions.eq("f.person1.id", personId));
        if(!c.list().isEmpty() && c.list().size()>0)
        {
            List<Friendship> listFriends = (ArrayList<Friendship>)c.list();
            if(!listFriends.isEmpty())
            {
                List<Integer> friendList = new ArrayList<Integer>();
                for(Friendship friend : listFriends){
                    friendList.add((int) friend.getPerson2().getId());
                }
                return friendList;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

}
