package Entities;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

public class Order {
    List<Photo> photos;
    BigInteger salonId;
    HashMap<String, String> params;//тут будем хранить статутс, даты, ещё что понадобиться
}
