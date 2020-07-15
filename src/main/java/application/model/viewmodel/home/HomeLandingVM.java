package application.model.viewmodel.home;

import application.model.viewmodel.common.ObjectProductVM;
import application.model.viewmodel.common.ProductPriceVM;
import application.model.viewmodel.common.ProductVM;
import application.model.viewmodel.news.NewsVM;

import java.util.List;

public class HomeLandingVM {
    private String keyWord;
    private List<ObjectProductVM> objectProductVM;
    private List<ProductVM> productVMList;
    private  List<NewsVM> newsVMList;
    private List<ProductPriceVM> productPriceVMList;



    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public List<ObjectProductVM> getObjectProductVM() {
        return objectProductVM;
    }

    public void setObjectProductVM(List<ObjectProductVM> objectProductVM) {
        this.objectProductVM = objectProductVM;
    }

    public List<ProductVM> getProductVMList() {
        return productVMList;
    }

    public void setProductVMList(List<ProductVM> productVMList) {
        this.productVMList = productVMList;
    }

    public List<NewsVM> getNewsVMList() {
        return newsVMList;
    }

    public void setNewsVMList(List<NewsVM> newsVMList) {
        this.newsVMList = newsVMList;
    }

    public List<ProductPriceVM> getProductPriceVMList() {
        return productPriceVMList;
    }

    public void setProductPriceVMList(List<ProductPriceVM> productPriceVMList) {
        this.productPriceVMList = productPriceVMList;
    }
}
