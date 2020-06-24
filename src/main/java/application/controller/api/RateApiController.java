package application.controller.api;

import application.data.model.Rate;
import application.data.service.ProductService;
import application.data.service.RateService;
import application.data.service.UserService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.dto.RateDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/rate")
public class RateApiController {
    private static final Logger logger = LogManager.getLogger(ProductPromotionApiController.class);
    @Autowired
    private RateService rateService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create")
    public BaseApiResult createRate(@RequestBody RateDTO dto){
        DataApiResult result = new DataApiResult();

        try {
            Rate rate = new Rate();
            rate.setStar(dto.getStar());
            rate.setUser(userService.findUserByUsername(dto.getUserName()));
            rate.setProduct(productService.findOne(dto.getProductId()));
            rateService.addNewRate(rate);
            result.setData(rate.getId());
            result.setMessage("Save product image successfully: " + rate.getId());
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/update/{rateId}")
    public BaseApiResult updateRate(@PathVariable int rateId,
                                        @RequestBody RateDTO dto) {
        BaseApiResult result = new BaseApiResult();

        try {
            Rate rate = rateService.findOne(rateId);
            rate.setStar(dto.getStar());
            rate.setUser(userService.findUserByUsername(dto.getUserName()));
            rate.setProduct(productService.findOne(dto.getProductId()));
            rateService.updateRate(rate);
            result.setSuccess(true);
            result.setMessage("Update product image successfully");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

}
