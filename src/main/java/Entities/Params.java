package Entities;

import java.io.Serializable;

public enum Params implements Serializable {
    ADDRESS,
    NAME,
    DESCRIPTION,
    OFFER_PRICE,
    TIME_TO_OFFER,
    ORDER_STATUS,
    EMAIL,
    PASSWORD,
    IS_ADMIN,
    IS_DELIVERY,//доставка или самовывоз
    ORDER_PRICE,
    ORDER_TIME,
    WORKER_STATUS,//True - свободен, false - занят, например
    WORK_TIME,
}