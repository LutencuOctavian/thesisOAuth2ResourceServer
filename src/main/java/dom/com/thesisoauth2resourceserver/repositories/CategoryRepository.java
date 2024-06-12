package dom.com.thesisoauth2resourceserver.repositories;

import dom.com.thesisoauth2resourceserver.enties.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
