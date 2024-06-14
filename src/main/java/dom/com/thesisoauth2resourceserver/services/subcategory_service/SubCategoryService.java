package dom.com.thesisoauth2resourceserver.services.subcategory_service;

import dom.com.thesisoauth2resourceserver.enties.CategoryEntity;
import dom.com.thesisoauth2resourceserver.enties.SubCategoryEntity;
import dom.com.thesisoauth2resourceserver.exceptions.CategoryException;
import dom.com.thesisoauth2resourceserver.exceptions.SubCategoryException;
import dom.com.thesisoauth2resourceserver.repositories.SubCategoryRepository;
import dom.com.thesisoauth2resourceserver.services.category_service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("subCategoryService")
public class SubCategoryService implements ISubCategoryService{

    private final SubCategoryRepository subCategoryRepository;
    private final ICategoryService categoryService;

    @Autowired
    public SubCategoryService(SubCategoryRepository subCategoryRepository,
                              @Qualifier("categoryService") ICategoryService categoryService) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryService = categoryService;
    }

    public void addNewSubCategory(Long idCategory, String subCategory){
        checkIfThisSubCategoryExistAlready(idCategory,  subCategory);
        CategoryEntity categoryFromDB = categoryService.findCategoryById(idCategory);
        subCategoryRepository.save(new SubCategoryEntity(subCategory, categoryFromDB));
    }

    public void updateSubCategory(Long idSubCategory, String subCategory){
        SubCategoryEntity subCategoryFromDB = findSubCategoryById(idSubCategory);
        subCategoryFromDB.setSubCategoryName(subCategory);
        subCategoryRepository.save(subCategoryFromDB);
    }

    public SubCategoryEntity findSubCategoryById(Long idSubCategory) {
        return subCategoryRepository.findSubCategoryById(idSubCategory)
                    .orElseThrow(()->new SubCategoryException("No such id: " + idSubCategory + "in subcategory table"));
    }

    public void deleteSubCategory(Long idSubCategory){
        SubCategoryEntity subCategoryFromDB = findSubCategoryById(idSubCategory);
        subCategoryRepository.delete(subCategoryFromDB);
    }

    public List<String> getAllSubCategoryForUser(Long userId){
        return subCategoryRepository.findAllSubCategoryByUserId(userId).orElseThrow(()-> new SubCategoryException("SubCategory table doesn't exist"));
    }

    private void checkIfThisSubCategoryExistAlready(Long categoryId, String subCategory) {
        List<String> subCategoryList = subCategoryRepository.findSubCategoryByName(categoryId, subCategory)
                .orElseThrow(()-> new SubCategoryException("Subcategory table is empty in dataBase"));

        if(!subCategoryList.isEmpty()){
            throw new CategoryException("This subcategory (" + subCategory + ") exist already in dataBase");
        }
    }
}
