package dom.com.thesisoauth2resourceserver.services.subcategory_service;

import dom.com.thesisoauth2resourceserver.enties.SubCategoryEntity;
import java.util.List;

public interface ISubCategoryService {

    void addNewSubCategory(Long idCategory, String subCategory);

    void updateSubCategory(Long idSubCategory, String subCategory);
    SubCategoryEntity findSubCategoryById(Long idSubCategory);
    void deleteSubCategory(Long idSubCategory);

    List<String> getAllSubCategoryForUser(Long userId);
}
