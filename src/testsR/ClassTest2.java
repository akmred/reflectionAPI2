package testsR;

@DescriptionClass(title = "Класс тестирования 2, Запуск: Ошибка, несколько методов с аннотацией: @BeforeSuite")
public class ClassTest2 {

    @BeforeSuite
    public static void testBeforeSuite(){
        System.out.println("testBeforeSuite");
    }
    @BeforeSuite
    public static void testBeforeSuite1(){
        System.out.println("testBeforeSuite");
    }

    @Test(description =  "Описание процедуры test1", prioritet = 3)
    public static void test1(){
        System.out.println("test1");
    }

    @Test(description =  "Описание процедуры test2", prioritet = 3)
    public static void test2(){
        System.out.println("test2");
    }

    @Test(description =  "Описание процедуры test3", prioritet = 66)
    public static void test3(){
        System.out.println("test3");
    }

    @Test(description =  "Описание процедуры test4", prioritet = 33)
    public static void test4(){
        System.out.println("test4");
    }

    @AfterSuite
    public static void testAfterSuite(){
        System.out.println("testAfterSuite");
    }

}
