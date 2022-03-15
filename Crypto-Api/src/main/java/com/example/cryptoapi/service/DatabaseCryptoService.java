package com.example.cryptoapi.service;

import com.example.cryptoapi.model.Crypto;
import com.example.cryptoapi.repository.CryptoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@EnableScheduling
@Slf4j
public class DatabaseCryptoService {
    private  static  final  String FIAT_CURRENCY = "pln";
    private  static  final  String PER_PAGE = "50";
    private  static final  String PAGE = "1";

    @Autowired
    private  CryptoService cryptoService;
    @Autowired
    private CryptoRepository cryptoRepository;
    @Autowired
    private RestTemplate restTemplate;


    @Scheduled(cron = "*/30 * * * * *")
    private  void  addCryptoToDatabase(){
        Optional<List<Crypto>> optionalCryptos = Optional.of(cryptoGetRest());
        cryptoRepository.saveAll(optionalCryptos.orElseThrow());

        // log
        String time  = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        log.info("add to dabtaase ${}",time);

    }

    public List<Crypto> cryptoGetRest() {
        final  String URL = "https://api.coingecko.com/api/v3/";
        ResponseEntity<List<Crypto>> rateResponse = restTemplate
                .exchange(URL + "coins/markets?"
                                + "vs_currency=" +  FIAT_CURRENCY + "&"
                                + "per_page=" + PER_PAGE +"&" +
                                "page=" + PAGE, HttpMethod.GET, null
                        , new ParameterizedTypeReference<List<Crypto>>() {
                        });
        List<Crypto> cryptoList = rateResponse.getBody();
        return rateResponse.getBody();
    }

}
