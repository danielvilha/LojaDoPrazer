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
@XStreamAlias("lojadoprazer.dto.ApproveForPurchases")
public class ApproveForPurchases {
    
    @XStreamImplicit
    List<ApproveForPurchase> approveForPurchases = new ArrayList<ApproveForPurchase>();

    public List<ApproveForPurchase> getApproveForPurchases() {
        return approveForPurchases;
    }

    public void setApproveForPurchases(List<ApproveForPurchase> approveForPurchases) {
        this.approveForPurchases = approveForPurchases;
    }
}
