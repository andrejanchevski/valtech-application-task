package ch.codeflair.application.mock.datasource;

import java.util.HashMap;

public class ImprovedMockConstants {

    private final HashMap<Long, String> customerMap;

    private static final String CUSTOMER_DATA_1 = "Name                Your                1234 Yourplace      ";
    private static final String CUSTOMER_DATA_2 = "Thommen             Christoph           4460 Gelterkinden   ";
    private static final String CUSTOMER_DATA_3 = "Zahler              Ben                 4450 Sissach        ";

    public ImprovedMockConstants() {
        this.customerMap = new HashMap<>();
        this.customerMap.put(1L, CUSTOMER_DATA_1);
        this.customerMap.put(2L, CUSTOMER_DATA_2);
        this.customerMap.put(3L, CUSTOMER_DATA_3);
    }

    public HashMap<Long, String> getCustomersMap() {
        return this.customerMap;
    }


}
