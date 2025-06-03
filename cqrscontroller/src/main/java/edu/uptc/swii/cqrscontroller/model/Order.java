/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.uptc.swii.cqrscontroller.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="`order`")
public class Order {
    @Id
    @Column(name="id")
    private String id;
    @Column(name="productname")
    private String productname;
    @Column(name="cuantity")
    private String cuantity;
    @Column(name="value")
    private String value;
    
    public Order() {
    }

    public Order(String id, String productname, String cuantity, String value) {
        this.id = id;
        this.productname = productname;
        this.cuantity = cuantity;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getCuantity() {
        return cuantity;
    }

    public void setCuantity(String cuantity) {
        this.cuantity = cuantity;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "Order [id=" + id + ", productname=" + productname + ", cuantity=" + cuantity + ", address="
                + value + "]";
    }
    
        

}


