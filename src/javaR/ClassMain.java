package javaR;

import testsR.ClassTests;
import testsR.ClassTest2;
import testsR.ClassTest3;
import testsR.TestAll;

public class ClassMain {

    public static void main(String[] args) {

        TestAll testAll = new TestAll();
        testAll.start(ClassTests.class);
        testAll.start(ClassTest3.class);
        testAll.start(ClassTest2.class);
    }

}
