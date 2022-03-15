package com.example.cryptoapi.service;

import com.example.cryptoapi.model.Crypto;
import com.example.cryptoapi.model.CryptoChart;

import java.util.List;

public interface CryptoService {
    public List<Crypto> getCrypto(int count);
    public  Crypto findById(String id);
    public CryptoChart getCurrencyDay(String id);
    public CryptoChart getCurrencyMonth(String id);
    public CryptoChart getCurrency3Month(String id);
    public CryptoChart getCurrencyYear(String id);
}
