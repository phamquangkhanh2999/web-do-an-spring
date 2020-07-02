package application.model.viewmodel.home;

import application.model.viewmodel.common.ObjectProductVM;

import java.util.List;

public class HomeLandingVM {
    private String keyWord;
    private List<ObjectProductVM> objectProductVM;


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


}
