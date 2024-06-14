package dom.com.thesisoauth2resourceserver.repositories;

import dom.com.thesisoauth2resourceserver.enties.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, Long> {
    @Query("select subCat " +
            "from SubCategoryEntity subCat " +
            "where subCat.id = :idSubCategory")
    Optional<SubCategoryEntity> findSubCategoryById(@Param("idSubCategory") Long idSubCategory);

    @Query("select subCat.subCategoryName " +
            "from SubCategoryEntity subCat " +
            "inner join CategoryEntity cat on cat.id = subCat.categoryEntity.id " +
            "where cat.userId = :userId ")
    Optional<List<String>> findAllSubCategoryByUserId(@Param("userId") Long userId);

    @Query("select subCat.subCategoryName " +
            "from SubCategoryEntity subCat " +
            "inner join CategoryEntity cat on cat.id = subCat.categoryEntity.id " +
            "where subCat.subCategoryName = :subCategory and cat.id = :categoryId")
    Optional<List<String>> findSubCategoryByName(@Param("categoryId")Long categoryId,
                                                 @Param("subCategory")String subCategory);
}
