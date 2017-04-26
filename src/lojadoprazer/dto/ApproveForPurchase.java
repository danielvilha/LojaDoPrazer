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
@XStreamAlias("lojadoprazer.dto.ApproveForPurchase")
public class ApproveForPurchase {
    
    @XStreamAlias("id")
    @XStreamAsAttribute
    private int id;

    public ApproveForPurchase() {
    }

    public ApproveForPurchase(int id) {
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
