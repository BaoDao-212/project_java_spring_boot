package com.amigoscode.order;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
public class Order {

    public Order(long long1, String string, LocalDate localDate) {
        //TODO Auto-generated constructor stub
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long order_id;
    @Column(name = "user_id")
    Long user_id;
    @Column(name = "order_date")
    Date order_date;
    /*
      order_id SERIAL PRIMARY KEY,
    user_id INT,
    order_date DATE,
    -- Other fields like shipping_address, total_amount, etc.
    FOREIGN KEY (user_id) REFERENCES users(id)
     */


}