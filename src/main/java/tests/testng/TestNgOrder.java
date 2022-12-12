package tests.testng;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Verifier;

import java.util.List;

public class TestNgOrder {

    @Test(priority=1)
    public void testB(List list){
        System.out.println("TestB");
        Assert.assertTrue(true);
        Assert.assertFalse(false);
        Assert.assertEquals(1, 2, "[ERR] Actual is not equal to expected");

        if(list.isEmpty()){
            Assert.fail("This test is failed.....");
        }
    }

    @Test(priority=2)
    public void testA(){
        System.out.println("TestA");
        Verifier.equals(1, 2);
    }

    @Test(dependsOnMethods = {"testA1"})
    public void testB1(){
        System.out.println("TestB1");
    }

    @Test()
    public void testA1(){
        System.out.println("TestA1");
        throw new RuntimeException("Err..............");
    }

    @Test
    public void testS(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(1, 1);
        softAssert.assertEquals(1, 2, "[ERR].........");
        softAssert.assertAll();
        System.out.println("Hello");
    }
}
