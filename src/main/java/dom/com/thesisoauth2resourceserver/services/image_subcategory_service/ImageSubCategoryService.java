package dom.com.thesisoauth2resourceserver.services.image_subcategory_service;

import dom.com.thesisoauth2resourceserver.enties.ImageEntity;
import dom.com.thesisoauth2resourceserver.enties.ImageSubCategoryEntity;
import dom.com.thesisoauth2resourceserver.enties.SubCategoryEntity;
import dom.com.thesisoauth2resourceserver.repositories.ImageSubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("imageSubCategoryService")
public class ImageSubCategoryService implements IImageSubCategoryService {

    private final ImageSubCategoryRepository imageSubCategoryRepository;

    @Autowired
    public ImageSubCategoryService(ImageSubCategoryRepository imageSubCategoryRepository) {
        this.imageSubCategoryRepository = imageSubCategoryRepository;
    }

    public void saveImageSubCategory(SubCategoryEntity subCategory, ImageEntity image){
        imageSubCategoryRepository.save(new ImageSubCategoryEntity(subCategory, image));
    }
}
