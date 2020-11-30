package edu.wit.comp1050;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.lang.module.Configuration;

public class JavaMain {

    public static void main(String[] args) throws ConfigurationException {
//        Card starter = new Card(12, Suit.H),
//                card1 = new Card(6, Suit.S),
//                card2 = new Card(5, Suit.S),
//                card3 = new Card(6, Suit.H),
//                card4 = new Card(6, Suit.D);
//
//        Hand h = new Hand(starter, card1, card2, card3, card4);
//
//        System.out.println(h.getScore());
//
//        System.out.println(starter.toString());
//
//        for(String s : h._scores)
//            if(s != null)
//                System.out.println(s);

        Configurations configs = new Configurations();

        PropertiesConfiguration config = configs.properties(new File("settings.properties"));

        double time = config.getDouble("clockTime");
        System.out.println(time);



    }
}