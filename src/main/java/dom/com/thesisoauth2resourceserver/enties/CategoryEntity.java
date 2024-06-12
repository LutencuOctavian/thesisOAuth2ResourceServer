package dom.com.thesisoauth2resourceserver.enties;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "categoryName")
    private String categoryName;

    @Column(name="userId")
    private Long userId;

    @OneToMany(mappedBy = "categoryEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SubCategoryEntity> subCategoryEntityList;

    public CategoryEntity() {}

    public CategoryEntity(String categoryName, Long userId) {
        this.categoryName = categoryName;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<SubCategoryEntity> getSubCategoryEntityList() {
        return subCategoryEntityList;
    }

    public void setSubCategoryEntityList(List<SubCategoryEntity> subCategoryEntityList) {
        this.subCategoryEntityList = subCategoryEntityList;
    }
}
