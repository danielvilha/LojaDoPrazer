/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danielvilha
 */
@XStreamAlias("lojadoprazer.dto.Purchases")
public class Purchases {
    
    @XStreamImplicit
    List<Purchase> purchases = new ArrayList<Purchase>();

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
}
