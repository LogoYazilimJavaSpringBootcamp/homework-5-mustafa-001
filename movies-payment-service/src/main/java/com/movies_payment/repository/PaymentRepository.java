package com.movies_payment.repository;

import com.movies_payment.dto.PaymentDto;
import com.movies_payment.entity.Payment;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insert;

    @Autowired
    public PaymentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insert = new SimpleJdbcInsert(jdbcTemplate);
        this.insert.setTableName("payment");
        this.insert.usingGeneratedKeyColumns("payment_id");
    }

    public Payment save(Payment paymentDto){
//        jdbcTemplate.update("INSERT INTO payment(user_id, payment_type) values (?, ?)", paymentDto.getUserId(), paymentDto.getPaymentType().ordinal());
        var parameters = new HashMap<String, Object>();
        parameters.put("user_id", paymentDto.getUserId());
        parameters.put("payment_type", paymentDto.getPaymentType().ordinal());
        parameters.put("payment_date", Timestamp.valueOf(paymentDto.getPaymentTime()));

        var id =  insert.executeAndReturnKey(parameters);
        return null; //findById(id).orElseThrow(() -> new IllegalStateException(""));
    }

    private Optional<Payment> findById(Number id) {
        return Optional.empty();
    }
}
