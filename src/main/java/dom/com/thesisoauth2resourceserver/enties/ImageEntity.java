package dom.com.thesisoauth2resourceserver.enties;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="images")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="location")
    private String location;

    @Column(name="date")
    private LocalDate date;

    @Column(name="latitude_degrees")
    private Double latitudeDegrees;

    @Column(name="longitude_degrees")
    private Double longitudeDegrees;

    @Enumerated(EnumType.STRING)
    @Column(name="latitude_hemisphere")
    private HisphereEnum latitudeHemisphere;

    @Enumerated(EnumType.STRING)
    @Column(name="longitude_hemisphere")
    private HisphereEnum longitudeHemisphere;

    @Column(name="annotation")
    private String annotation;

    @Column(name="image_base64")
    private String imageBase64;

    @OneToMany(mappedBy = "imageEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ImageSubCategoryEntity> imageSubCategoryEntityList;

    public ImageEntity() {}

    public ImageEntity(String location, LocalDate date, Double latitudeDegrees,
                       Double longitudeDegrees, HisphereEnum latitudeHemisphere,
                       HisphereEnum longitudeHemisphere, String annotation, String imageBase64) {
        this.location = location;
        this.date = date;
        this.latitudeDegrees = latitudeDegrees;
        this.longitudeDegrees = longitudeDegrees;
        this.latitudeHemisphere = latitudeHemisphere;
        this.longitudeHemisphere = longitudeHemisphere;
        this.annotation = annotation;
        this.imageBase64 = imageBase64;
    }

    public enum HisphereEnum {
        NORTH("NORTH"),
        EAST("EAST"),
        WEST("WEST"),
        SOUTH("SOUTH");
        private final String label;

        HisphereEnum(String grantType) {
            this.label = grantType;
        }

        public static HisphereEnum valueOfLabel(String label) {
            for (HisphereEnum hemisphere : values()) {
                if (hemisphere.label.equals(label)) {
                    return hemisphere;
                }
            }
            return null;
        }

        public static List<HisphereEnum> getAllValuesOfEnum() {
            return List.of(values());
        }

        @Override
        public String toString() {
            return label;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getLatitudeDegrees() {
        return latitudeDegrees;
    }

    public void setLatitudeDegrees(Double latitudeDegrees) {
        this.latitudeDegrees = latitudeDegrees;
    }

    public Double getLongitudeDegrees() {
        return longitudeDegrees;
    }

    public void setLongitudeDegrees(Double longitudeDegrees) {
        this.longitudeDegrees = longitudeDegrees;
    }

    public HisphereEnum getLatitudeHemisphere() {
        return latitudeHemisphere;
    }

    public void setLatitudeHemisphere(HisphereEnum latitudeHemisphere) {
        this.latitudeHemisphere = latitudeHemisphere;
    }

    public HisphereEnum getLongitudeHemisphere() {
        return longitudeHemisphere;
    }

    public void setLongitudeHemisphere(HisphereEnum longitudeHemisphere) {
        this.longitudeHemisphere = longitudeHemisphere;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public List<ImageSubCategoryEntity> getImageSubCategoryEntityList() {
        return imageSubCategoryEntityList;
    }

    public void setImageSubCategoryEntityList(List<ImageSubCategoryEntity> imageSubCategoryEntityList) {
        this.imageSubCategoryEntityList = imageSubCategoryEntityList;
    }
}
