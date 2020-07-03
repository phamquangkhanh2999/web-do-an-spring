package application.controller.web;

import application.data.model.Product;
import application.data.model.ProductImage;
import application.data.model.SizeColor;
import application.data.service.ProductService;
import application.model.viewmodel.common.ProductImageVM;
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

    @GetMapping(value = "/{productId}")
    public String home(@PathVariable Integer productId, Model model,
                       @Valid @ModelAttribute("productname") ProductVM productNamee){
        ProductDetailVM vm = new ProductDetailVM();

        Product productEntity = productService.findOne(productId);
        ProductVM productVM = new ProductVM();
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
                sizeColorVM.setPrice(sizeColor.getPrice());
                sizeColorVM.setColor(sizeColor.getColor());
                sizeColorVM.setAmount(sizeColor.getAmount());

                sizeColorVMList.add(sizeColorVM);
            }

            productVM.setSizeColorVMList(sizeColorVMList);
        }


        vm.setProductVM(productVM);

        model.addAttribute("vm",vm);

        return "detail";
    }
}
