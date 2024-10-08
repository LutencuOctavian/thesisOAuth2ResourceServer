package dom.com.thesisoauth2resourceserver.services.category_service;

import dom.com.thesisoauth2resourceserver.enties.CategoryEntity;

import java.util.List;

public interface ICategoryService {
    void createNewCategory(Long userId, String category);

    void updateCategory(Long categoryId, String category);

    void deleteCategory(Long categoryId);

    List<String> getAllCategoriesForUser(Long userId);

    CategoryEntity findCategoryById(Long categoryId);
}
