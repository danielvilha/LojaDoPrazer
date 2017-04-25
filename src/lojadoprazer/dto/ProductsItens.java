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
@XStreamAlias("lojadoprazer.dto.ProductsItens")
public class ProductsItens {
    
    @XStreamImplicit
    List<ProductItem> productsItens = new ArrayList<ProductItem>();

    public List<ProductItem> getProductsItens() {
        return productsItens;
    }

    public void setProductsItens(List<ProductItem> productsItens) {
        this.productsItens = productsItens;
    }

    
}
