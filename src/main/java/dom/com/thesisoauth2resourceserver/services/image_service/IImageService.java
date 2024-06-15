package dom.com.thesisoauth2resourceserver.services.image_service;

import dom.com.thesisoauth2resourceserver.dto.ImageDTO;
import dom.com.thesisoauth2resourceserver.enties.ImageEntity;
import java.util.List;

public interface IImageService {
    void saveNewImage(Long subCategoryId, ImageDTO imageDTO);

    void updateImage(ImageDTO imageDTO);
    ImageEntity findImageEntityById(Long imageId);
    void deleteImage(Long imageId);
    List<ImageDTO> gelAllImageForUser(Long userId);
}
