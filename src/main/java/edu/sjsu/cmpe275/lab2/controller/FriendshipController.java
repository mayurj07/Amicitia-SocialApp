package edu.sjsu.cmpe275.lab2.controller;

import edu.sjsu.cmpe275.lab2.model.Person;
import edu.sjsu.cmpe275.lab2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


public class FriendshipController {


    @Autowired
    PersonService personService;

    @RequestMapping(value = "/friends/{id1}/{id2}", method = RequestMethod.PUT)
    public ResponseEntity<?> createFriend(Model model, @PathVariable Integer id1, @PathVariable Integer id2) {

        String result = "";
        if (id1 != null && id2 != null && id1 != id2) {
            try {
                Person p1 = personService.getPersonInfo(id1);
                Person p2 = personService.getPersonInfo(id2);
                if (p1 != null && p2 != null) {
                    Frienship f = friendService.search(id1, id2);
                    if (f == null) {
                        f = new Frienship();
                        f.setPerson1(p1);
                        f.setPerson2(p2);
                        friendService.add(f);
                        ObjectMapper mapper = new ObjectMapper();
                        result = mapper.writeValueAsString(f);
                        return new ResponseEntity<>(result, HttpStatus.OK);
                    } else {
                        result = "Friendship already exists.";
                        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
                    }
                } else {
                    result = "Person(/s) does not exist.";
                    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("Exception Occured", HttpStatus.BAD_REQUEST);
            }
        } else {
            result = "Please provide valid inputs.";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

	/* - - - - Delete Friend API - - - - */

    @RequestMapping(value = "/friends/{id1}/{id2}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteFriend(Model model, @PathVariable Integer id1, @PathVariable Integer id2) {
        String result = "";
        if (id1 != null && id2 != null && id1 != id2) {
            try {

                Person p1 = personService.getPerson(id1);
                Person p2 = personService.getPerson(id2);

                if (p1 != null && p2 != null) {

                    Frienship f = friendService.search(id1, id2);
                    if (f != null) {
                        friendService.delete(f);
                        ObjectMapper mapper = new ObjectMapper();
                        result = mapper.writeValueAsString(f);
                        return new ResponseEntity<>(result, HttpStatus.OK);
                    } else {
                        result = "Friendship does not exist between these two persons";
                        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
                    }
                } else {
                    result = "Friendship does not exist between these two persons";
                    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
                }

            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("Exception Occured", HttpStatus.BAD_REQUEST);
            }
        } else {
            result = "Please provide valid inputs.";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
