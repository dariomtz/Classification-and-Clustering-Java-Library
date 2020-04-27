package demo;

import org.junit.Test;
import org.junit.Assert;

public class LibraryTest {
    @Test public void testSomeLibraryMethod(){
        Library classUnderTest = new Library();
        Assert.assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
    }
}
