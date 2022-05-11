import org.junit.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


/**
 * @author 80230531
 * @date 2022/5/11 11:21
 */
public class MathCalTest {
    static MathCal mathCal;

    @BeforeClass
    public static void setUpBeforeClass() {
        System.out.println("setUpBeforeClass");
        mathCal = new MathCal();
    }

    @AfterClass
    public static void tearDownAfterClass() {
        System.out.println("tearDownAfterClass");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("setup before");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("teardown after");
    }

    @Test
    public void factorial() throws Exception {
        assertThat(mathCal.factorial(5), equalTo(120));
    }

    @Test
    public void fibonacci() {
    }
}