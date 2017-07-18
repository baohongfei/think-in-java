package com.baohongfei.jdk8;

import java.time.Clock;
import java.util.Arrays;

/**
 * Created by terry on 10/07/17.
 */
public class Lambda {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println("hello world");


        Arrays.asList("a", "b", "d").forEach(e -> System.out.println(e));

        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );

        final Clock clock = Clock.systemUTC();
        System.out.println( clock.instant() );
        System.out.println( clock.millis() );
        Thread
    }
}
