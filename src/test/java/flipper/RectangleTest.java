package flipper;

import org.junit.Test;
import static org.junit.Assert.*;

public class RectangleTest{
    @Test public void testIsInTheShape() {
      Position E=new Position(1.5,1.5);
      Position F= new Position(2.5,2.5);
      Rectangle r=new Rectangle(0.5,new Position(0.5,1),new Position(1.5,2),new Position(2,1.5),new Position(1,0.5));
      assertTrue(r.isInTheShape(E));
      assertTrue(!r.isInTheShape(F));
    }
}
