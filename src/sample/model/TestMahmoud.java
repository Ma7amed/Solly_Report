package sample.model;

/**
 * Created by m80028770 on 8/14/2017.
 */
public class TestMahmoud {



    public static void main(String[] args) {

        Dog d1 = new Dog();
        Dog d2 = new Dog();

        d1.height = 10;
        d2.height = 20;


        System.out.println("TestMahmoud.main d1:" + d1.height);
        System.out.println("TestMahmoud.main d2:" + d2.height);


        d1 = d2;

        System.out.println("TestMahmoud.main d1:" + d1.height);
        System.out.println("TestMahmoud.main d2:" + d2.height);


        d2.height = 999;

        System.out.println("TestMahmoud.main d1:" + d1.height);
        //System.out.println("TestMahmoud.main d2:" + d2.height);


    }


    /*

    public static void main(String[] args) {

        int x;
        int y;

        x=10;
        y=20;

        System.out.println("TestMahmoud.main x:" + x);
        System.out.println("TestMahmoud.main y:" + y);


        x=y;


        System.out.println("TestMahmoud.main x:" + x);
        System.out.println("TestMahmoud.main y:" + y);


        y=999;


        System.out.println("TestMahmoud.main x:" + x);
        System.out.println("TestMahmoud.main y:" + y);

    }

*/

}
