package org.trollheim.commons.math;

import org.junit.Assert;
import org.junit.Test;


public class PointTest {


    @Test
    public void findVector() {
    }

   @Test
   public void shift() {
        Point a = new Point(1,1);
        Point b = new Point(2,2);
        Point c = b.shift(a);
        System.out.println(c.point[0]);
       System.out.println(c.point[1]);
        Assert.assertEquals(c.point[0],1f,10e-6);
        Assert.assertEquals(c.point[1],1f,10e-6);
    }
}
