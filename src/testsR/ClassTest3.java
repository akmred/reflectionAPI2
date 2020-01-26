package testsR;

@DescriptionClass(title = "Класс тестирования 3. Запуск: test2(), (test3(), test4(), test1() - один приоритет)," +
        "testAfterSuite()")
public class ClassTest3 {

    @Test(description =  "Описание процедуры test1", prioritet = 2)
    public static void test1(){
        System.out.println("test1");
    }

    @Test(description =  "Описание процедуры test2", prioritet = 1)
    public static void test2(){
        System.out.println("test2");
    }

    @Test(description =  "Описание процедуры test3", prioritet = 2)
    public static void test3(){
        System.out.println("test3");
    }

    @Test(description =  "Описание процедуры test4", prioritet = 2)
    public static void test4(){
        System.out.println("test4");
    }

    @AfterSuite
    public static void testAfterSuite(){
        System.out.println("testAfterSuite");
    }


}
