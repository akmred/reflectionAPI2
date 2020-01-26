package testsR;

import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestAll {

    public TestAll() {
    }

    /*
    * Запускает все методы класса в установленном порядке
     * 1. @BeforeSuite
     * 2. @Tests
     * 1. @AfterSuite
     */
    public static void start(Class testClass){

            if(!testClass.isAnnotationPresent(DescriptionClass.class)){
                throw new RuntimeException("bad class");
            }

            System.out.println("Обрабатываем класс: " + testClass.getName());
        System.out.println("Описание: " +
                        ((DescriptionClass)testClass.getAnnotation(DescriptionClass.class)).title());

            Method[] methods = testClass.getMethods();

            List<Method> methodsBeforeSuite = new LinkedList<>();
            // для сортировки используется коллекция TreeMap
            Map<Integer, List<Method>> methodsTests = new TreeMap<Integer, List<Method>>();
            List<Method> methodsAfterSuite = new LinkedList<>();

            for (Method m:methods) {
                if(m.isAnnotationPresent(BeforeSuite.class)){
                    methodsBeforeSuite.add(m);
                }
                else if(m.isAnnotationPresent(Test.class)){

                    int priority = m.getAnnotation(Test.class).prioritet();

                    if(!methodsTests.containsKey(priority)){
                        methodsTests.put(priority, new LinkedList<Method>());
                    }

                    List<Method> list = methodsTests.get(priority);
                    list.add(m);
                    methodsTests.put(priority, list);
                    }
                else  if(m.isAnnotationPresent(AfterSuite.class)){
                    methodsAfterSuite.add(m);
                }

            }

        if(methodsBeforeSuite.size() > 1){
            throw new RuntimeException("Обнаружено более одного метода с аннотацией: @BeforeSuite");
        }
        if(methodsAfterSuite.size() > 1){
            throw new RuntimeException("Обнаружено более одного метода с аннотацией: @AfterSuite");
        }

        ////////////////////////////BeforeSuite/////////////////////////////////

        for (Method m:methodsBeforeSuite) {

            if(m.isAnnotationPresent(BeforeSuite.class)){

                try {
                    m.invoke(testClass);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        //////////////////////////@Tests///////////////////////////////////


        for (List<Method> m: methodsTests.values()) {
            try {
                for (int i = 0; i < m.size() ; i++) {
                    m.get(i).invoke(testClass);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }


        /*
        for (int i = 0; i < methodsTests.size(); i++) {

            Method m = methodsTests.put();

            if(m.isAnnotationPresent(Test.class)){

                try {
                    m.invoke(testClass);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        }
*/
        //////////////////////////@AfterTests///////////////////////////////////

        for (Method m:methodsAfterSuite) {

            if(m.isAnnotationPresent(AfterSuite.class)){

                try {
                    m.invoke(testClass);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private static int nextOrderRun(int prioritet, Map<Integer, Method> map){

        int nextPrioritet = prioritet;

        if(map.containsKey(prioritet)){
            nextPrioritet += 1;
            nextOrderRun(nextPrioritet, map);
        }

        return nextPrioritet;

    }
}
