package dom.com.thesisoauth2resourceserver.services.category_service;

import dom.com.thesisoauth2resourceserver.enties.CategoryEntity;
import dom.com.thesisoauth2resourceserver.exceptions.CategoryException;
import dom.com.thesisoauth2resourceserver.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("categoryService")
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void createNewCategory(Long userId, String category){
        checkIfThisCategoryExistAlready(userId, category);
        categoryRepository.save(new CategoryEntity(category, userId));
    }

    public void updateCategory(Long categoryId, String category){
        CategoryEntity categoryFromDB = findCategoryById(categoryId);

        categoryFromDB.setCategoryName(category);
        categoryRepository.save(categoryFromDB);
    }

    public void deleteCategory(Long categoryId){
        CategoryEntity categoryFromDB = findCategoryById(categoryId);
        categoryRepository.delete(categoryFromDB);
    }

    public List<String> getAllCategoriesForUser(Long userId){
        return categoryRepository.findAllCategoriesForUser(userId).orElseThrow(()-> new CategoryException("Category table doesn't exist"));
    }

    private void checkIfThisCategoryExistAlready(Long userId, String category) {
        List<String> categoryList = categoryRepository.findCategoryByName(userId, category)
                .orElseThrow(() -> new CategoryException("Category table is empty in dataBase"));

        if(categoryList.contains(category)){
            throw new CategoryException("This category (" + category + ") exist already in dataBase");
        }
    }

    private CategoryEntity findCategoryById(Long categoryId) {
        return categoryRepository.findCategoryById(categoryId)
                .orElseThrow(() -> new CategoryException("No such category id: " + categoryId + " in database"));
    }
}
