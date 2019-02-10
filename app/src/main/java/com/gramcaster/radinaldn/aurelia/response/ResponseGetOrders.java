package com.gramcaster.radinaldn.aurelia.response;

/**
 * Created by radinaldn on 10/02/19.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.gramcaster.radinaldn.aurelia.model.Order;

public class ResponseGetOrders {

    @SerializedName("order")
    @Expose
    private List<Order> order = null;

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

}