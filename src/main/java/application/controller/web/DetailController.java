package application.controller.web;

import application.data.model.Product;
import application.data.model.ProductImage;
import application.data.model.ProductPromotion;
import application.data.model.SizeColor;
import application.data.service.ProductPromotionService;
import application.data.service.ProductService;
import application.data.service.SizeColorService;
import application.model.viewmodel.common.ProductImageVM;
import application.model.viewmodel.common.ProductPriceVM;
import application.model.viewmodel.common.ProductVM;
import application.model.viewmodel.common.SizeColorVM;
import application.model.viewmodel.productdetail.ProductDetailVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/chi-tiet")
public class DetailController {

    @Autowired
    ProductService productService;

    @Autowired
    SizeColorService sizeColorService;

    @Autowired
    ProductPromotionService productPromotionService;

    @GetMapping(value = "/{productId}/{color}" )
    public String home(@PathVariable Integer productId,@PathVariable String color, Model model,
                       @Valid @ModelAttribute("productname") ProductVM productNamee){
        ProductDetailVM vm = new ProductDetailVM();

        Product productEntity = productService.findOne(productId);
        ProductVM productVM = new ProductVM();
        ProductPriceVM productPriceVM = new ProductPriceVM();
        if(productEntity!=null) {
            productVM.setId(productEntity.getId());
            productVM.setName(productEntity.getName());
            productVM.setMainImage(productEntity.getMainImage());
            productVM.setShortDesc(productEntity.getShortDesc());
            productVM.setManufacturer(productEntity.getManufacturer());
            productVM.setModel(productEntity.getModel());
            productVM.setScreen(productEntity.getScreen());
            productVM.setResolution(productEntity.getResolution());
            productVM.setDescription(productEntity.getDescription());
            productVM.setCpu(productEntity.getCpu());
            productVM.setRam(productEntity.getRam());
            productVM.setCamera(productEntity.getCamera());
            productVM.setPin(productEntity.getPin());
            productVM.setOther(productEntity.getOther());
            productVM.setCreatedDate(productEntity.getCreateDate());
            productVM.setYearGuaratee(productEntity.getYearGuaratee());
            productVM.setCategoryId(productEntity.getCategory().getId());
            double price = 0;
            List<SizeColor> sizeColorEntitys = sizeColorService.getProductByColor(productId,color);
            if(sizeColorEntitys!=null){
                for(SizeColor sizeColor: sizeColorEntitys){
                    price =sizeColor.getPrice();
                }
            }
            productPriceVM.setPrice(price);
            double discount  = 0;
            List<ProductPromotion> productPromotions = productPromotionService.getProductByPromotion(productId);
            if(productPromotions!=null){
                for(ProductPromotion productPromotion: productPromotions){
                    discount = price * (1 - productPromotion.getDiscount());
                }
            }
            productPriceVM.setDiscount(discount);

            /**
             * set list product image vm
             */
            List<ProductImageVM> productImageVMS = new ArrayList<>();
            for(ProductImage productImage : productEntity.getProductImageList()) {
                ProductImageVM productImageVM = new ProductImageVM();
                productImageVM.setLink(productImage.getLink());

                productImageVMS.add(productImageVM);
            }
            productVM.setProductImageList(productImageVMS);
            /**
             * set list color vm
             */
            List<SizeColorVM> sizeColorVMList = new ArrayList<>();
            for(SizeColor sizeColor : productEntity.getListSizeColor()) {
                SizeColorVM sizeColorVM = new SizeColorVM();
                sizeColorVM.setId(sizeColor.getId());
                sizeColorVM.setPrice(sizeColor.getPrice());
                sizeColorVM.setColor(sizeColor.getColor());
                sizeColorVM.setAmount(sizeColor.getAmount());

                sizeColorVMList.add(sizeColorVM);
            }

            productVM.setSizeColorVMList(sizeColorVMList);
        };



        System.out.println(productPriceVM.getPrice());
        vm.setProductPriceVM(productPriceVM);
        vm.setProductVM(productVM);

        model.addAttribute("vm",vm);

        return "detail";
    }
}
