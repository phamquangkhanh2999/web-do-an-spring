package application.controller.web;

import application.data.model.*;
import application.data.service.*;
import application.model.viewmodel.common.ObjectProductVM;
import application.model.viewmodel.common.ProductVM;
import application.model.viewmodel.home.HomeLandingVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController extends BaseController {
    @Autowired
    ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    SizeColorService sizeColorService;

    @Autowired
    ProductPromotionService productPromotionService;


    @Autowired
    RateService rateService;

    @GetMapping(value = "/")
    public String home(Model model,
                       @Valid @ModelAttribute("productname") ObjectProductVM productName,
                       @RequestParam(name = "categoryId", required = false) Integer categoryId,
                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                       @RequestParam(name = "size", required = false, defaultValue = "12") Integer size,
                       @RequestParam(name = "sortByPrice", required = false) String sort,
                       HttpServletResponse response,
                       HttpServletRequest request,
                       final Principal principal){

        this.checkCookie(response, request, principal);
        HomeLandingVM vm = new HomeLandingVM();

        Sort sortable = new Sort(Sort.Direction.ASC,"id");
        if(sort != null) {
            if (sort.equals("ASC")) {
                sortable = new Sort(Sort.Direction.ASC,"price");
            }else {
                sortable = new Sort(Sort.Direction.DESC,"price");
            }
        }


        Pageable pageable = new PageRequest(page, size, sortable);

        Page<Product> productPage = null;


        if(categoryId != null) {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable,categoryId,null);
            Category category = categoryService.findOne(categoryId);
            vm.setKeyWord(category.getName());
        } else if (productName.getName() != null && !productName.getName().isEmpty()) {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable,null,productName.getName().trim());
            vm.setKeyWord("Find with key: " + productName.getName());
        } else {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable,null,null);
        }
        List<ObjectProductVM> objectProductVMS = new ArrayList<>();
        for(Product product : productPage.getContent()){
                ObjectProductVM productVM = new ObjectProductVM();
                productVM.setId(product.getId());
                productVM.setName(product.getName());
                productVM.setMainImage(product.getMainImage());
                if(product.getCategory() == null) {
                    productVM.setCategoryId(0);
                } else {
                    productVM.setCategoryId(product.getCategory().getId());
                }
                productVM.setShortDesc(product.getShortDesc());
                for(SizeColor sizeColor: product.getListSizeColor()){
                   productVM.setPrice(sizeColor.getPrice());
                   productVM.setColor(sizeColor.getColor());
                   productVM.setAmount(sizeColor.getAmount());

                   break;
                }
                List<ProductPromotion> productPromotions = productPromotionService.getProductByPromotion(product.getId());
                if(productPromotions!=null){
                    for(ProductPromotion productPromotion: productPromotions){
                        productVM.setDiscount(productPromotion.getDiscount());
                        productVM.setStartDate(productPromotion.getPromotion().getStart_date());
                        productVM.setEndDate(productPromotion.getPromotion().getEnd_date());
                    }
                }

                List<Integer> rateList = rateService.getRateByProduct(product);
                if(rateList != null && rateList.size() >  0){
                    productVM.setSlRate(rateList.size());
                    int diem = 0;
                    for (Integer int1: rateList){
                        diem += int1.intValue();
                    }
                    double tb = (double) diem/(rateList.size());
                    productVM.setTbRate(tb);
                } else {
                    productVM.setSlRate(0);
                    productVM.setTbRate(0.0);
                }

            objectProductVMS.add(productVM);
        }
        if(objectProductVMS.size() == 0) {
            vm.setKeyWord("Not found any product");
        }
        vm.setObjectProductVM(objectProductVMS);
        model.addAttribute("vm",vm);
        model.addAttribute("page",productPage);
        return "home";
    }
}
