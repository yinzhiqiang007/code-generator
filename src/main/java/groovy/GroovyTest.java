package groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class GroovyTest {

    public static void main(String[] args) {
        int n = 1;
        String s = "ss";
        String g1="10>2&&2<1";
        String g2="a,b,c,d,s";
        String g3="\"a,b,c,d,s\".indexOf(\"${n}\")>0";
//        String g33 = String.format(g3,"d");


        String g4="'aa'=='aa1'";

        Binding binding = new Binding();
        binding.setProperty("min",1);
        binding.setProperty("max",10);
        binding.setProperty("s",s);
        binding.setProperty("n","a");
        binding.setProperty("ttt",2>1);
        GroovyShell groovyShell = new GroovyShell(binding);
        Object o1 = groovyShell.evaluate(g1);
        Object o3 = groovyShell.evaluate(g3);
//        Object o4 = groovyShell.evaluate(g4);
        System.out.println(o1);
        System.out.println(o3);
//        System.out.println(o4);


    }
}
