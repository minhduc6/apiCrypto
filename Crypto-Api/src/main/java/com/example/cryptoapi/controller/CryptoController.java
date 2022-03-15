package com.example.cryptoapi.controller;

import com.example.cryptoapi.model.Crypto;
import com.example.cryptoapi.model.CryptoChart;
import com.example.cryptoapi.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class CryptoController {
    @Autowired
    private CryptoService cryptoService;


    @GetMapping("/list")
    public String getCrypto(@RequestParam String count , Model model) {
        List<Crypto> cryptoList = cryptoService.getCrypto(Integer.parseInt(count));
        model.addAttribute("cryptoList",cryptoList);
        return "TrangChu";
    }
//    @GetMapping("/cryto/grap/{symbol}")
//    public String graphCrypto(@PathVariable String symbol ,Model model) {
//        String upperCase = symbol.toUpperCase();
//        model.addAttribute("title","Grap " + upperCase);
//        model.addAttribute("symbol",upperCase);
//        model.addAttribute("money","USD,EUR,CNY,GBP");
//        return "graph";
//    }
    @GetMapping("/cryto/grap")
    public String graphCrypto(@RequestParam String id,Model model) {
        Crypto crypto =cryptoService.findById(id);
        model.addAttribute("crypto",crypto);
        CryptoChart cryptoChart = cryptoService.getCurrencyDay(id);
        List<String> timeDay = cryptoChart.covertTimeDay(cryptoChart.getTime());
        List<Double> priceDay = cryptoChart.getPrice();
        model.addAttribute("timeArrDay",timeDay);
        model.addAttribute("priceArrDay",priceDay);
        CryptoChart cryptoChartMonth = cryptoService.getCurrencyMonth(id);
        List<String> timeMonth = cryptoChartMonth.covertTimeMonth(cryptoChartMonth.getTime());
        List<Double> priceMonth = cryptoChartMonth.getPrice();
        model.addAttribute("timeArrMonth",timeMonth);
        model.addAttribute("priceArrMonth",priceMonth);
        CryptoChart cryptoChart3Month = cryptoService.getCurrency3Month(id);
        List<String> time3Month = cryptoChart3Month.covertTimeMonth(cryptoChart3Month.get3MonthTime());
        List<Double> price3Month = cryptoChart3Month.get3MonthPrice();
        model.addAttribute("timeArr3Month",time3Month);
        model.addAttribute("priceArr3Month",price3Month);
        CryptoChart cryptoChart12Month = cryptoService.getCurrencyYear(id);
        List<String> time12Month = cryptoChart12Month.covertTimeMonth(cryptoChart12Month.get12MonthTime());
        List<Double> price12Month = cryptoChart12Month.get12MonthPrice();
        model.addAttribute("timeArr12Month",time12Month);
        model.addAttribute("priceArr12Month",price12Month);
        return "index";
    }
}
