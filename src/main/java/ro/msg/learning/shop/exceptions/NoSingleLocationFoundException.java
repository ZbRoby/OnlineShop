package ro.msg.learning.shop.exceptions;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
public class NoSingleLocationFoundException extends RuntimeException {

    public NoSingleLocationFoundException() {
        super();
    }

    public NoSingleLocationFoundException(String message) {
        super(message);
    }
}
