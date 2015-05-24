package tests;

import confguration.PropertyConfig;


public class PropertyConfigTest {
    static PropertyConfig config = new PropertyConfig();



    public static void main(String args[]){
        System.out.println(config.get_port());
    }
}
