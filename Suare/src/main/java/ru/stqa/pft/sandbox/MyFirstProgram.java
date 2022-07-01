package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main (String [] args){
        hello("world");
        hello("Sergei");
        hello("user");

        Square s = new Square(7);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

        Rectangle f = new Rectangle(6,9);
        System.out.println("Площадь прямоугольника со сторонами " + f.a +" и "+ f.b + " = " + f.area());

    }

    public static void hello (String somebody){
        System.out.println("Hello,"+ somebody+"!");
    }


}
