package com.sg.guessnum;

import com.sg.guessnum.controller.GuessNumController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//public class App implements CommandLineRunner
public class App
{
    @Autowired
    GuessNumController controller;

    public static void main( String[] args )
    {
        SpringApplication.run( App.class, args );
        //TODO: uncomment when method rdy
        //controller.run()
    }
}
