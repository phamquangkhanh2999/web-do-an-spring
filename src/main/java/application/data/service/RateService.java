package application.data.service;

import application.data.model.Product;
import application.data.model.Rate;
import application.data.model.SizeColor;
import application.data.repository.RateRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RateService {
    private static final Logger logger = LogManager.getLogger(SizeColorService.class);

    @Autowired
    private RateRepository rateRepository;

    @Transactional
    public void addNewListRates(List<Rate> rates) {
        try {
            rateRepository.save(rates);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void addNewRate(Rate rate) {
        try {
            rateRepository.save(rate);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public Rate findOne(int rateId) {

        return rateRepository.findOne(rateId);
    }

    public boolean updateRate(Rate rate) {
        try {
            rateRepository.save(rate);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean deleteRate(int rateId) {
        try {
            rateRepository.delete(rateId);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }
    public List<Integer> getRateByProduct(Product product){
        return rateRepository.getRateByProduct(product);
    }

}
