package edu.sjsu.cmpe275.lab2.controller;

import edu.sjsu.cmpe275.lab2.model.Friendship;
import edu.sjsu.cmpe275.lab2.model.Person;
import edu.sjsu.cmpe275.lab2.service.FriendshipService;
import edu.sjsu.cmpe275.lab2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/friends")
public class FriendshipController {


    @Autowired
    PersonService personService;

    @Autowired
    FriendshipService friendshipService;

    @RequestMapping(value = "/{person1Id}/{person2Id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Object> makeFriends(
            @PathVariable(value = "person1Id") Integer person1Id,
            @PathVariable(value = "person2Id") Integer person2Id) {

        if (person1Id != person2Id) {
            try {
                Person p1 = personService.getPersonInfo(person1Id);
                Person p2 = personService.getPersonInfo(person2Id);
                if (p1 != null && p2 != null) {

                    Friendship friendship = friendshipService.checkIfFriends(person1Id, person2Id);

                    if (friendship == null) {
                        friendship = new Friendship();
                        friendship.setPerson1(p1);
                        friendship.setPerson2(p2);

                        friendshipService.add(friendship);

                        return new ResponseEntity<Object>("Friends Now..!! :-)", HttpStatus.OK);
                    } else {
                        return new ResponseEntity<Object>("Persons are already friends.", HttpStatus.OK);
                    }
                } else {
                    return new ResponseEntity<Object>("Person does not exist.", HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<Object>("Exception Occurred", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<Object>("Person cannot be friends with himself", HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/{person1Id}/{person2Id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Object> deleteFriend(@PathVariable Integer person1Id, @PathVariable Integer person2Id) {
        if (person1Id != person2Id) {
            try {

                Person p1 = personService.getPersonInfo(person1Id);
                Person p2 = personService.getPersonInfo(person2Id);

                if (p1 != null && p2 != null) {

                    Friendship friendship = friendshipService.checkIfFriends(person1Id, person2Id);

                    if (friendship != null) {
                        friendshipService.delete(friendship);
                        return new ResponseEntity<Object>("Friends No More..!! :-(", HttpStatus.OK);
                    } else {
                        return new ResponseEntity<Object>("Not Friends.", HttpStatus.NOT_FOUND);
                    }
                } else
                    return new ResponseEntity<Object>("Either Person does not exist.", HttpStatus.NOT_FOUND);


            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<Object>("Exception Occurred", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<Object>("Person cannot unfriend himself", HttpStatus.BAD_REQUEST);
        }
    }
}
