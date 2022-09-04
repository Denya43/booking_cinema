package com.example.booking_cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

    @GetMapping("/cinema")
    public String cinema() {

        return "";
    }

}
