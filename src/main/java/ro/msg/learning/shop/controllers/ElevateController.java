package ro.msg.learning.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.services.Elevate;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@RestController
public class ElevateController {

    private final Elevate elevate;

    @Autowired
    public ElevateController(Elevate elevate) {
        this.elevate = elevate;
    }

    @PutMapping(value = "rest/elevate/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void elevate(@PathVariable("id") long id) {
        elevate.elevate(id);
    }


}
