package dom.com.thesisoauth2resourceserver.services.gps_service;

import dom.com.thesisoauth2resourceserver.dto.GPSDTO;
import org.apache.commons.imaging.ImageReadException;
import java.io.IOException;

public interface IGPSService {
    GPSDTO getGPSDataForImage(String imageBase64) throws IOException, ImageReadException;
}
