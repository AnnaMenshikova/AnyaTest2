import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FirstTest {
    //кратно 3, возвращать "Т"
    //кратно 5, возвращать "М"
    //кратно 3 и 5, возвращать "ТИМ"
    //возвращать "FAIL"

    public String trialCode(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return "ТИМ";
        } else if (number % 5 == 0) {
            return "М";
        } else if (number % 3 == 0) {
            return "Т";
        } else return "FAIL";
    }

    @Test
    public void checkNumber() {
        String actualResult = trialCode(3);
        assertEquals(actualResult,"Т");
    }

    @Test
    public void checkNumber2() {
        String actualResult = trialCode(25);
        assertEquals(actualResult,"М");
    }

    @Test
    public void checkNumber3() {
        String actualResult = trialCode(15);
        assertEquals(actualResult,"ТИМ");
    }

    @Test
    public void checkNumber4() {
        String actualResult = trialCode(13);
        assertEquals(actualResult,"FAIL");
    }
}
