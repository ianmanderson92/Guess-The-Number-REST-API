/*
  Ian Anderson
  7/21/2022
  Java Classes and Objects
  c262-classwork
 */

package com.sg.guessnum.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
//Spring creates a bean called UserIOConsoleImpl
public class UserIOConsoleImpl implements UserIO
{
    //@Autowired
    Scanner inputReader = new Scanner(System.in);

    @Override
    public void print(String message)
    {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt)
    {
        String inString;
        System.out.println(prompt);
        inString = inputReader.nextLine();
        return inString;
    }

    @Override
    public int readInt(String prompt)
    {
        int inNum;
        System.out.println(prompt);
        inNum = Integer.parseInt(inputReader.nextLine());
        return inNum;
    }

    @Override
    public int readInt(String prompt, int min, int max)
    {
        int inNum;
        System.out.println(prompt);
        inNum = Integer.parseInt(inputReader.nextLine());
        while (inNum < min || inNum > max)
        {
            System.out.println("Input number must be within range [" + min + " - " + max + "]");
            inNum = Integer.parseInt(inputReader.nextLine());
        }
        return inNum;
    }

    @Override
    public double readDouble(String prompt)
    {
        double inNum;
        System.out.println(prompt);
        inNum = Double.parseDouble(inputReader.nextLine());
        return inNum;
    }

    @Override
    public double readDouble(String prompt, double min, double max)
    {
        double inNum;
        System.out.println(prompt);
        inNum = Double.parseDouble(inputReader.nextLine());
        while (inNum < min || inNum > max)
        {
            System.out.println("Input number must be within range [" + min + " - " + max + "]");
            inNum = Double.parseDouble(inputReader.nextLine());
        }
        return inNum;
    }

    @Override
    public float readFloat(String prompt)
    {
        float inNum;
        System.out.println(prompt);
        inNum = Float.parseFloat(inputReader.nextLine());
        return inNum;
    }

    @Override
    public float readFloat(String prompt, float min, float max)
    {
        float inNum;
        System.out.println(prompt);
        inNum = Float.parseFloat(inputReader.nextLine());
        while (inNum < min || inNum > max)
        {
            System.out.println("Input number must be within range [" + min + " - " + max + "]");
            inNum = Float.parseFloat(inputReader.nextLine());
        }
        return inNum;
    }

    @Override
    public long readLong(String prompt)
    {
        long inNum;
        System.out.println(prompt);
        inNum = Long.parseLong(inputReader.nextLine());
        return inNum;
    }

    @Override
    public long readLong(String prompt, long min, long max)
    {
        long inNum;
        System.out.println(prompt);
        inNum = Long.parseLong(inputReader.nextLine());
        while (inNum < min || inNum > max)
        {
            System.out.println("Input number must be within range [" + min + " - " + max + "]");
            inNum = Long.parseLong(inputReader.nextLine());
        }
        return inNum;
    }

    @Override
    public BigDecimal readBigDecimal( String prompt )
    {
        System.out.println( prompt );
        BigDecimal bd = new BigDecimal( inputReader.nextLine() );
        return bd;
    }
}
