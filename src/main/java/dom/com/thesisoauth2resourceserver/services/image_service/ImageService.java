package dom.com.thesisoauth2resourceserver.services.image_service;

import dom.com.thesisoauth2resourceserver.converters.GenericConverterType;
import dom.com.thesisoauth2resourceserver.dto.ImageDTO;
import dom.com.thesisoauth2resourceserver.enties.ImageEntity;
import dom.com.thesisoauth2resourceserver.enties.SubCategoryEntity;
import dom.com.thesisoauth2resourceserver.exceptions.ImageException;
import dom.com.thesisoauth2resourceserver.repositories.ImageRepository;
import dom.com.thesisoauth2resourceserver.services.image_subcategory_service.IImageSubCategoryService;
import dom.com.thesisoauth2resourceserver.services.subcategory_service.ISubCategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("imageService")
public class ImageService implements IImageService{

    private final GenericConverterType genericConverterType;
    private final ImageRepository imageRepository;
    private final ISubCategoryService subCategoryService;
    private final IImageSubCategoryService imageSubCategoryService;

    @Autowired
    public ImageService(GenericConverterType genericConverterType, ImageRepository imageRepository,
                        @Qualifier("subCategoryService") ISubCategoryService subCategoryService,
                        @Qualifier("imageSubCategoryService") IImageSubCategoryService imageSubCategoryService) {
        this.genericConverterType = genericConverterType;
        this.imageRepository = imageRepository;
        this.subCategoryService = subCategoryService;
        this.imageSubCategoryService = imageSubCategoryService;
    }
    @Transactional
    public void saveNewImage(Long subCategoryId, ImageDTO imageDTO){
        Converter<ImageDTO, ImageEntity> converter = (Converter<ImageDTO, ImageEntity>)genericConverterType.getConverter(ImageEntity.class);
        ImageEntity imageEntity = converter.convert(imageDTO);
        ImageEntity imageEntityFromDB = imageRepository.save(imageEntity);
        SubCategoryEntity subCategory = subCategoryService.findSubCategoryById(subCategoryId);
        imageSubCategoryService.saveImageSubCategory(subCategory, imageEntityFromDB);
    }

    @Transactional
    public void updateImage(ImageDTO imageDTO){
        Converter<ImageDTO, ImageEntity> converter = (Converter<ImageDTO, ImageEntity>)genericConverterType.getConverter(ImageEntity.class);
        ImageEntity imageEntityFromUser = converter.convert(imageDTO);
        ImageEntity imageEntityFromDB = findImageEntityById(imageDTO.getId());
        copyDataFromUserIntoDBEntity(imageEntityFromUser, imageEntityFromDB);
        imageRepository.save(imageEntityFromDB);
    }

    public ImageEntity findImageEntityById(Long imageId) {
        return imageRepository.findImageEntityById(imageId)
                .orElseThrow(()-> new ImageException("No such ImageEntity with id: " + imageId + " in database"));
    }

    @Transactional
    public void deleteImage(Long imageId){
        ImageEntity imageEntity = findImageEntityById(imageId);
        imageRepository.delete(imageEntity);
    }

    public List<ImageDTO> gelAllImageForUser(Long userId){
        List<ImageEntity> listOfImagesForUser = imageRepository.findAllImagesForUser(userId)
                .orElseThrow(() -> new ImageException("User with id: " + userId + " has some SQL exception"));
        Converter<ImageEntity, ImageDTO> converter = (Converter<ImageEntity, ImageDTO>)genericConverterType.getConverter(ImageDTO.class);
        return listOfImagesForUser.parallelStream()
                .map(converter::convert)
                .toList();
    }

    private void copyDataFromUserIntoDBEntity(ImageEntity imageUser, ImageEntity imageDB) {
        imageDB.setId(imageUser.getId());
        imageDB.setLocation(imageUser.getLocation());
        imageDB.setDate(imageUser.getDate());
        imageDB.setLatitudeDegrees(imageUser.getLatitudeDegrees());
        imageDB.setLongitudeDegrees(imageUser.getLongitudeDegrees());
        imageDB.setLatitudeHemisphere(imageUser.getLatitudeHemisphere());
        imageDB.setLongitudeHemisphere(imageUser.getLongitudeHemisphere());
        imageDB.setAnnotation(imageUser.getAnnotation());
        imageDB.setImageArrayOfBytes(imageUser.getImageArrayOfBytes());
    }
}
