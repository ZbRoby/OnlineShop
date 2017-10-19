package ro.msg.learning.shop.exceptions;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
public class QuantityExceedsStockException extends RuntimeException {

    private final Long productId;
    private final Long askedQuantity;
    private final Long stockQuantity;

    public QuantityExceedsStockException() {
        super();
        this.productId = null;
        this.askedQuantity = null;
        this.stockQuantity = null;
    }

    public QuantityExceedsStockException(String message) {
        super(message);
        this.productId = null;
        this.askedQuantity = null;
        this.stockQuantity = null;
    }

    public QuantityExceedsStockException(long productId, long askedQuantity, long stockQuantity) {
        super("The product:" + productId + " wants:" + askedQuantity + " has only:" + stockQuantity);
        this.productId = productId;
        this.askedQuantity = askedQuantity;
        this.stockQuantity = stockQuantity;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getAskedQuantity() {
        return askedQuantity;
    }

    public Long getStockQuantity() {
        return stockQuantity;
    }
}
