package Entities;

import java.util.HashMap;
import java.util.List;

public class Salon {
    String name;
    String description;
    String address;
    List<Equipment> equipments;//or  List<BigInteger> equipmentIds; смотря что удобнее и быстрее, этот же коммент к другим спискам в полях классов
    List<Worker> workers;
    List<Order> orders;
    List<Offer> offers;
    HashMap<String,String> params;//адрес, рабочий режим - надо будет придумать формат чтобы парсить при отображении, пример Понедельник?00:00-12:00#Вторник ......
}
