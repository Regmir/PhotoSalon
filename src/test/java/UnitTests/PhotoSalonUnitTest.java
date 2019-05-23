package UnitTests;

import Entities.*;
import org.junit.*;

import java.math.BigInteger;

public class PhotoSalonUnitTest {

    @Test
    public void EquipmentTest() {
        Equipment equipment = new Equipment("test", new EquipmentType("test"));
        equipment.setName("TEST");
        Assert.assertEquals("TEST", equipment.getName());
    }

    @Test
    public void EquipmentTypeTest() {
        EquipmentType equipmentType = new EquipmentType("test");
        Offer offer = new Offer("testOffer");
        equipmentType.getAvailableOffers().add(offer);
        Assert.assertEquals(offer, equipmentType.getAvailableOffers().get(0));
    }

    @Test
    public void OfferTest() {
        Offer offer = new Offer("test");
        offer.getParams().put(Params.OFFER_PRICE, "10");
        Assert.assertEquals("10", offer.getParams().get(Params.OFFER_PRICE));
    }

    @Test
    public void OrderTest() {
        Order order = new Order();
        order.setSalonId(new BigInteger("10"));
        Assert.assertEquals(new BigInteger("10"), order.getSalonId());
    }

    @Test
    public void PhotoTest() {
        Photo photo = new Photo();
        Offer offer = new Offer("testOffer");
        photo.getAppliedOffers().add(offer);
        Assert.assertEquals(offer, photo.getAppliedOffers().get(0));
    }

    @Test
    public void SalonTest() {
        Salon salon = new Salon("test");
        Equipment equipment = new Equipment("test", new EquipmentType("test"));
        salon.getEquipments().add(equipment);
        Assert.assertEquals(equipment, salon.getEquipments().get(0));
    }

    @Test
    public void UserTest() {
        User user = new User("test", "test", "test", "no");
        Order order = new Order();
        user.getOrders().add(order);
        Assert.assertEquals(order, user.getOrders().get(0));
    }

    @Test
    public void WorkerTest() {
        Worker worker = new Worker("test");
        worker.setSalonId(new BigInteger("10"));
        Assert.assertEquals(new BigInteger("10"), worker.getSalonId());
    }

    @Test
    public void CalcOrderPriceTest() {
        Order order = new Order();
        Offer offer1 = new Offer("test1");
        offer1.getParams().put(Params.OFFER_PRICE, "10");
        Offer offer2 = new Offer("test2");
        offer2.getParams().put(Params.OFFER_PRICE, "15");
        Photo photo = new Photo();
        photo.getAppliedOffers().add(offer1);
        photo.getAppliedOffers().add(offer2);
        order.getPhotos().add(photo);
        Assert.assertEquals(new Double(25), order.getOrderPrice());
    }

    @Test
    public void OrderStatusTest() {
        Salon salon = new Salon("test");
        Worker worker = new Worker("worker");
        salon.getWorkers().add(worker);
        Assert.assertEquals(worker, salon.getWorkers().get(0));
    }

    @Test
    public void AvailableOffersTest() {
        EquipmentType equipmentType = new EquipmentType("test");
        Equipment equipment = new Equipment("name", equipmentType);
        Assert.assertEquals(equipmentType, equipment.getEquipmentType());
    }

    @Test
    public void WorkTimeTest() {
        Salon salon = new Salon("test");
        salon.getParams().put(Params.WORK_TIME, "12-12");
        Assert.assertEquals("12-12", salon.getParams().get(Params.WORK_TIME));
    }

    @Test
    public void WorkerStatusTest() {
        Worker worker = new Worker("test");
        worker.getParams().put(Params.WORKER_STATUS, "True");
        Assert.assertEquals(Boolean.TRUE, Boolean.valueOf(worker.getParams().get(Params.WORKER_STATUS)));
    }
}
