package dom.com.thesisoauth2resourceserver.services.image_subcategory_service;

import dom.com.thesisoauth2resourceserver.enties.ImageEntity;
import dom.com.thesisoauth2resourceserver.enties.SubCategoryEntity;

public interface IImageSubCategoryService {
    void saveImageSubCategory(SubCategoryEntity subCategory, ImageEntity image);
}
