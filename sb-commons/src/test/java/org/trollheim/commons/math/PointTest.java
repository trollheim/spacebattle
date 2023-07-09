package org.trollheim.commons.math;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointTest {


    @Test
    public void findVector() {
    }

   @Test
   public void shift() {
       Point a = new Point(1, 1);
       Point b = new Point(2, 2);
       Point c = b.shift(a);

       assertEquals(c.point[0], 1f, 10e-6);
       assertEquals(c.point[1], 1f, 10e-6);
   }
}
