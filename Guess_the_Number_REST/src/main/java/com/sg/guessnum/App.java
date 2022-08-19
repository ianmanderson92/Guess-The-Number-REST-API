package com.sg.guessnum;

import com.sg.guessnum.controller.GuessNumController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner
{
    @Autowired
    GuessNumController controller;

    public static void main( String[] args )
    {
        SpringApplication.run( App.class, args );
    }

    @Override
    public void run( String... args ) throws Exception
    {
        controller.run();
    }
}
