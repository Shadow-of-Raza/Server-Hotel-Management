package com.hotelmanagementsystem.dao;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="paymenttransaction")
public class PaymentTransaction 
{
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer transactionid;

      private String paymentid;
      
      private Integer roomid;
      
      private Integer customerid;
      
      private Date transactiondate;
      
      private Time transactiontime;
      
      private Double transactionamount;
	
      public PaymentTransaction() {
		super();
	}
	
	public PaymentTransaction(String paymentid, Integer roomid, Integer customerid, Date transactiondate,
			Time transactiontime, Double transactionamount) {
		super();
		this.paymentid = paymentid;
		this.roomid = roomid;
		this.customerid = customerid;
		this.transactiondate = transactiondate;
		this.transactiontime = transactiontime;
		this.transactionamount = transactionamount;
	}

	public Integer getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(Integer transactionid) {
		this.transactionid = transactionid;
	}
	
	public String getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}

	public Integer getRoomid() {
		return roomid;
	}

	public void setRoomid(Integer roomid) {
		this.roomid = roomid;
	}

	public Integer getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}

	public Date getTransactiondate() {
		return transactiondate;
	}
	public void setTransactiondate(Date transactiondate) {
		this.transactiondate = transactiondate;
	}
	public Time getTransactiontime() {
		return transactiontime;
	}
	public void setTransactiontime(Time transactiontime) {
		this.transactiontime = transactiontime;
	}
	public Double getTransactionamount() {
		return transactionamount;
	}
	public void setTransactionamount(Double transactionamount) {
		this.transactionamount = transactionamount;
	}

	@Override
	public String toString() {
		return "PaymentTransaction [transactionid=" + transactionid + ", paymentid=" + paymentid + ", roomid=" + roomid
				+ ", customerid=" + customerid + ", transactiondate=" + transactiondate + ", transactiontime="
				+ transactiontime + ", transactionamount=" + transactionamount + "]";
	}
	
	
      
}
