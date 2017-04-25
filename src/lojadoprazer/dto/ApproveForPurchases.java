/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 *
 * @author danielvilha
 */
@XStreamAlias("employee")
public class ApproveForPurchases {
    
    @XStreamAlias("id")
    @XStreamAsAttribute
    private int id;

    public ApproveForPurchases() {
    }

    public ApproveForPurchases(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Id: " + id;
    }
}
