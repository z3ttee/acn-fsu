package com.pluralsight.speldemo.controller;

import com.pluralsight.speldemo.data.City;
import com.pluralsight.speldemo.data.Order;
import com.pluralsight.speldemo.data.Shipping;
import com.pluralsight.speldemo.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    Order order;

    @Autowired
    User user;

    @Autowired
    Shipping shipping;

    @Autowired
    City city;

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String getCustomer() {
        return user.getName();
    }

    @RequestMapping(value = "/amount", method = RequestMethod.GET)
    public double getOrderAmount() {
        return order.getAmount();
    }

    @RequestMapping(value = "/amount/formatted", method = RequestMethod.GET)
    public String getFormattedAmount() {
        return order.getFormattedAmount();
    }

    @RequestMapping(value = "/discount", method = RequestMethod.GET)
    public double getDiscount() {
        return order.getDiscount();
    }

    @RequestMapping(value = "/origin", method = RequestMethod.GET)
    public String getOrigin() {
        return order.getOrigin();
    }

    @RequestMapping(value = "/delivery", method = RequestMethod.GET)
    public int getDaysToDeliver() {
        return order.getDaysToDeliver();
    }

    @RequestMapping(value = "/shipping/locations", method = RequestMethod.GET)
    public List<City> getShippingLocations() {
        return order.getShippingLocations();
    }

    @RequestMapping(value = "/summary", method = RequestMethod.GET)
    public String getSummary() {
        return order.getOrderSummary();
    }

}
