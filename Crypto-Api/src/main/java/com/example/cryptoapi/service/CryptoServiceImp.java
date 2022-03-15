package com.example.cryptoapi.service;

import com.example.cryptoapi.model.Crypto;
import com.example.cryptoapi.model.CryptoChart;
import com.example.cryptoapi.repository.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CryptoServiceImp implements CryptoService{

    @Autowired
    private  CryptoRepository cryptoRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Crypto> getCrypto(int count) {
        return cryptoRepository.findAll();
    }

    @Override
    public Crypto findById(String id) {
        return cryptoRepository.findById(id).orElseThrow();
    }

    @Override
    public CryptoChart getCurrencyMonth(String id) {
        ResponseEntity<CryptoChart> rateResponse = restTemplate
                .exchange("https://api.coingecko.com/api/v3/coins/"+id+"/market_chart?vs_currency=pln&days=30"
                        ,HttpMethod.GET,null,new ParameterizedTypeReference<>(){});
        CryptoChart cryptoChartMonth = rateResponse.getBody();
        return cryptoChartMonth;
    }

    @Override
    public CryptoChart getCurrency3Month(String id) {
        ResponseEntity<CryptoChart> rateResponse = restTemplate
                .exchange("https://api.coingecko.com/api/v3/coins/"+id+"/market_chart?vs_currency=pln&days=90"
                        ,HttpMethod.GET,null,new ParameterizedTypeReference<>(){});
        CryptoChart cryptoChart3Month = rateResponse.getBody();
        return cryptoChart3Month;
    }

    @Override
    public CryptoChart getCurrencyYear(String id) {
        ResponseEntity<CryptoChart> rateResponse = restTemplate
                .exchange("https://api.coingecko.com/api/v3/coins/"+id+"/market_chart?vs_currency=pln&days=365"
                        ,HttpMethod.GET,null,new ParameterizedTypeReference<>(){});
        CryptoChart cryptoChartYear = rateResponse.getBody();
        return cryptoChartYear;
    }

    @Override
    public CryptoChart getCurrencyDay(String id ){
        ResponseEntity<CryptoChart> rateResponse = restTemplate
                .exchange("https://api.coingecko.com/api/v3/coins/"+id+"/market_chart?vs_currency=pln&days=1"
                        ,HttpMethod.GET,null,new ParameterizedTypeReference<>(){});
        CryptoChart cryptoChartDay = rateResponse.getBody();
        return cryptoChartDay;
    }
}
