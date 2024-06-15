package dom.com.thesisoauth2resourceserver.controllers;

import dom.com.thesisoauth2resourceserver.dto.ImageDTO;
import dom.com.thesisoauth2resourceserver.services.image_service.IImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/image")
public class ImageController {

    private final IImageService imageService;

    @Autowired
    public ImageController(IImageService imageService) {
        this.imageService = imageService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(path="/new-image/{subCategoryId}", method = RequestMethod.POST)
    public ResponseEntity<Object> addNewImage(@PathVariable(name = "subCategoryId") Long subCategoryId,
                                              @RequestBody @Valid ImageDTO imageDTO){
        imageService.saveNewImage(subCategoryId, imageDTO);
        return ResponseEntity.ok("Super okkkkk!  :-))");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(path="/update-image", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateImage(@RequestBody @Valid ImageDTO imageDTO){
        imageService.updateImage(imageDTO);
        return ResponseEntity.ok("Super okkkkk!  :-))");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(path="/delete-image/{imageId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteImage(@PathVariable("imageId") Long imageId){
        imageService.deleteImage(imageId);
        return ResponseEntity.ok("Super okkkkk!  :-))");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(path="/all-image", method = RequestMethod.GET)
    public ResponseEntity<Object> gelAllImageForUser(AbstractOAuth2TokenAuthenticationToken<?> auth){
        Long userId = getUserIdFromAuthorizationToken(auth);
        List<ImageDTO> imageList = imageService.gelAllImageForUser(userId);
        return ResponseEntity.ok(imageList);
    }

    private Long getUserIdFromAuthorizationToken(AbstractOAuth2TokenAuthenticationToken<?> auth) {
        Map<String, Object> tokenAttributes = auth.getTokenAttributes();
        return (Long) tokenAttributes.get("userId");
    }
}
