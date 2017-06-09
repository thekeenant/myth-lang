package com.keenant.myth;

import com.keenant.myth.codegen.ClassName;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClassNameTest {
    @Test
    public void test() {
        ClassName animal = new ClassName("Animal");

        ClassName dog = new ClassName("Dog", animal);
        ClassName cat = new ClassName("Cat", animal);

        assertTrue(dog.isSubTypeOf(animal));
        assertTrue(cat.isSubTypeOf(animal));

        assertFalse(animal.isSubTypeOf(dog));

        assertTrue(cat.isSubTypeOf(ClassName.OBJECT));
    }
}
