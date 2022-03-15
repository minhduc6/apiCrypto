package com.example.cryptoapi.repository;


import com.example.cryptoapi.model.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface CryptoRepository extends JpaRepository<Crypto, String> {
}
