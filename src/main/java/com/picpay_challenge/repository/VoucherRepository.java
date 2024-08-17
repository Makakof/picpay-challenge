package com.picpay_challenge.repository;

import com.picpay_challenge.entities.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<Voucher, Long>
{
}
