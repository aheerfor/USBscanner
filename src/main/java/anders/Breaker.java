package anders;

import org.apache.log4j.Logger;

public class Breaker {
    private static final Logger logger = Logger.getLogger(Breaker.class.getName());
    public static void breaker() {
        //Main.report();
        logger.trace("break");
    }
}
