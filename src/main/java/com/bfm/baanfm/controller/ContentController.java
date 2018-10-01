package com.bfm.baanfm.controller;

import com.bfm.baanfm.payload.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cont")
public class ContentController {
    @Autowired
    private static final String apiKey = "AIzaSyBkJdLHbX3EKE0jGcc77LuJ7eXe3lUyP0k";

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/key")
    @PreAuthorize("hasRole('USER')")
    public GMKey gmKey(){
        return new GMKey(apiKey);
    }

}
