package dom.com.thesisoauth2resourceserver.repositories;

import dom.com.thesisoauth2resourceserver.enties.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query("select cat.categoryName " +
            "from CategoryEntity cat " +
            "where cat.categoryName = :category and cat.userId = :userId")
    Optional<List<String>> findCategoryByName(@Param("userId") Long userId,
                                              @Param("category") String category);

    @Query("select cat " +
            "from CategoryEntity cat " +
            "where cat.id = :categoryId")
    Optional<CategoryEntity> findCategoryById(@Param("categoryId") Long categoryId);

    @Query("select cat.categoryName " +
            "from CategoryEntity cat " +
            "where cat.userId = :userId")
    Optional<List<String>> findAllCategoriesForUser(@Param("userId") Long userId);
}
