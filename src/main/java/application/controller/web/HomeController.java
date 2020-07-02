package application.controller.web;

import application.data.model.Category;
import application.data.service.CategoryService;
import application.data.service.ProductService;
import application.model.viewmodel.common.ObjectProductVM;
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

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/")
    public String home(Model model,
                       @Valid @ModelAttribute("productname") ObjectProductVM productName,
                       @RequestParam(name = "categoryId", required = false) Integer categoryId,
                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                       @RequestParam(name = "size", required = false, defaultValue = "12") Integer size,
                       @RequestParam(name = "sortByPrice", required = false) String sort){

        HomeLandingVM vm = new HomeLandingVM();
//        Sort sortable = new Sort(Sort.Direction.ASC,"id");
//        if(sort != null) {
//            if (sort.equals("ASC")) {
//                sortable = new Sort(Sort.Direction.ASC,"price");
//            }else {
//                sortable = new Sort(Sort.Direction.DESC,"price");
//            }
//        }
        List<Object[]> list = productService.getProductByPromotion();

        List<ObjectProductVM> objectProductVMS = new ArrayList<>();
        for(Object[] objects : list){
            ObjectProductVM productVM =  new ObjectProductVM();
            productVM.setId((Integer) objects[0]);
            productVM.setName((String) objects[1]);
            productVM.setShortDesc((String) objects[2]);
            productVM.setMainImage((String) objects[3]);
            productVM.setPrice((Double) objects[4]);
            productVM.setColor((String) objects[5]);
            productVM.setAmount((Integer) objects[6]);
            productVM.setDiscount((Double) objects[7]);
            productVM.setStartDate((Date) objects[8]);
            productVM.setEndDate((Date) objects[9]);
            productVM.setSlRate((BigInteger) objects[10]);
            productVM.setTbRate((BigDecimal) objects[11]);
            productVM.setCategoryId((Integer) objects[12]);
            objectProductVMS.add(productVM);
        }

        vm.setObjectProductVM(objectProductVMS);
        model.addAttribute("vm",vm);
        return "home";
    }
}
