package application.controller.api;

import application.data.model.Product;
import application.data.model.SizeColor;
import application.data.service.ProductService;
import application.data.service.SizeColorService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.dto.SizeColorDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(path = "/api/size-color")
public class SizeColorApiController {
    private static final Logger logger = LogManager.getLogger(SizeColorApiController.class);
    @Autowired
    private SizeColorService sizeColorService;

    @Autowired
    private ProductService productService;

    private String[] arrColor = {"Trắng", "Xanh","Vàng","Đen","Bạch Kim","Hồng"};
    private String[] arrSize = {"64GB", "128GB","256GB"};

    @GetMapping("/fake")
    public BaseApiResult fakeProductImage() {
        BaseApiResult result = new BaseApiResult();

        try {
            Random random = new Random();
            List<Product> productList = productService.getListAllProducts();
            for(Product product : productList) {
                if(product.getListSizeColor().size() == 0) {
                    List<SizeColor> sizeColors = new ArrayList<>();
                    for (int i=0; i<random.nextInt(3) +1 ; i++) {

                        SizeColor sizeColor = new SizeColor();
                        /**
                         * random price
                         */
                        double rangeMin = 4;
                        double rangeMax = 30;
                        double randomPrice = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
                        DecimalFormat f = new DecimalFormat("##.00");
                        String priceStr  = f.format(randomPrice);
                        randomPrice = Double.parseDouble(String.valueOf(priceStr));
                        sizeColor.setPrice(randomPrice);

                        sizeColor.setProduct(product);

                        sizeColor.setColor(arrColor[random.nextInt(arrColor.length)]);
                        sizeColor.setSize(arrSize[random.nextInt(arrSize.length)]);
                        int amountMin = 1;
                        int amountMax = 50;
                        int randomAmount = (int) (amountMin + Math.floor((amountMax - amountMin) * random.nextDouble()));
                        sizeColor.setAmount(randomAmount);

                        sizeColors.add(sizeColor);

                    }
                    sizeColorService.addNewListSizeColors(sizeColors);
                }
            }
            result.setSuccess(true);
            result.setMessage("Fake list size color successfully !");
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            logger.error(e.getMessage());
        }
        return result;
    }
    @PostMapping(value = "/create")
    public BaseApiResult createSizeColor(@RequestBody SizeColorDTO dto){
        DataApiResult result = new DataApiResult();

        try {
            SizeColor sizeColor = new SizeColor();
            sizeColor.setProduct(productService.findOne(dto.getProductId()));
            sizeColor.setSize(dto.getSize());
            sizeColor.setColor(dto.getColor());
            sizeColor.setAmount(dto.getAmount());
            sizeColor.setPrice(dto.getPrice());
            sizeColorService.addNewSizeColor(sizeColor);
            result.setData(sizeColor.getId());
            result.setMessage("Save product image successfully: " + sizeColor.getId());
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/update/{sizeColorId}")
    public BaseApiResult updateCategory(@PathVariable int sizeColorId,
                                        @RequestBody SizeColorDTO dto) {
        BaseApiResult result = new BaseApiResult();

        try {
            SizeColor sizeColor = sizeColorService.findOne(sizeColorId);
            sizeColor.setProduct(productService.findOne(dto.getProductId()));
            sizeColor.setSize(dto.getSize());
            sizeColor.setColor(dto.getColor());
            sizeColor.setAmount(dto.getAmount());
            sizeColor.setPrice(dto.getPrice());
            sizeColorService.updateSizeColor(sizeColor);
            result.setSuccess(true);
            result.setMessage("Update product image successfully");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }
    @GetMapping("/detail/{sizeColorId}")
    public BaseApiResult detailSizeColor(@PathVariable int sizeColorId){
        DataApiResult result= new DataApiResult();

        try {
            SizeColor sizeColorEntity = sizeColorService.findOne(sizeColorId);
            if (sizeColorEntity == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this product");
            } else {
                SizeColorDTO dto = new SizeColorDTO();
                dto.setId(sizeColorEntity.getId());
                if(sizeColorEntity.getProduct() != null) {
                    dto.setProductId(sizeColorEntity.getProduct().getId());
                }
                dto.setAmount(sizeColorEntity.getAmount());
                dto.setColor(sizeColorEntity.getColor());
                dto.setPrice(sizeColorEntity.getPrice());
                dto.setSize(sizeColorEntity.getSize());
                result.setSuccess(true);
                result.setData(dto);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }
    @GetMapping("/delete/{sizeColorId}")
    public BaseApiResult deleteSizeColor(@PathVariable int sizeColorId){
        DataApiResult result= new DataApiResult();
        try {
            sizeColorService.deleteSizeColor(sizeColorId);
            result.setMessage("Delete success :"+ sizeColorId);
            result.setSuccess(true);
            result.setData(sizeColorId);

        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

}
