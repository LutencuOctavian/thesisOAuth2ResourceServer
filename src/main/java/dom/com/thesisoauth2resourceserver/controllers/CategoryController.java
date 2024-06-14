package dom.com.thesisoauth2resourceserver.controllers;

import dom.com.thesisoauth2resourceserver.services.category_service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {

    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(@Qualifier("categoryService") ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(path = "/new-category", method = RequestMethod.POST)
    public ResponseEntity<Object> addNewCategory(@RequestParam("category") String category, AbstractOAuth2TokenAuthenticationToken<?> auth) throws ParseException {
        Long userId = getUserIdFromAuthorizationToken(auth);
        categoryService.createNewCategory(userId, category);
        return ResponseEntity.ok("SUPPER");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(path = "/update-category/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCategory(@RequestParam("category") String category, @PathVariable(name = "id") Long categoryId ) throws ParseException {
        categoryService.updateCategory(categoryId, category);
        return ResponseEntity.ok("SUPPER");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(path = "/delete-category/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteCategory(@PathVariable(name = "id") Long categoryId ) throws ParseException {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("SUPPER");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(path = "/get-all-category", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllCategoryForUser(AbstractOAuth2TokenAuthenticationToken<?> auth) throws ParseException {
        Long userId = getUserIdFromAuthorizationToken(auth);
        List<String> allCategoriesForUser = categoryService.getAllCategoriesForUser(userId);
        return ResponseEntity.ok(allCategoriesForUser);
    }

    private Long getUserIdFromAuthorizationToken(AbstractOAuth2TokenAuthenticationToken<?> auth) {
        Map<String, Object> tokenAttributes = auth.getTokenAttributes();
        return (Long) tokenAttributes.get("userId");
    }
}
