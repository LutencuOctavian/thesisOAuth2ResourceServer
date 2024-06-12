package dom.com.thesisoauth2resourceserver.repositories;

import dom.com.thesisoauth2resourceserver.enties.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
}
