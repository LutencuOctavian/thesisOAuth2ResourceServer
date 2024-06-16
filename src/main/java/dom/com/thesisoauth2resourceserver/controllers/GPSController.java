package dom.com.thesisoauth2resourceserver.controllers;

import dom.com.thesisoauth2resourceserver.dto.GPSDTO;
import dom.com.thesisoauth2resourceserver.dto.ImageDTO;
import dom.com.thesisoauth2resourceserver.services.gps_service.IGPSService;
import org.apache.commons.imaging.ImageReadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping(path="/gps")
public class GPSController {

    private final IGPSService gpsService;

    @Autowired
    public GPSController(@Qualifier("gpsService") IGPSService gpsService) {
        this.gpsService = gpsService;
    }

    @RequestMapping(path="/get-data-image", method = RequestMethod.POST)
    public ResponseEntity<Object> getGPSDataFromImage(@RequestBody ImageDTO imageDTO) throws IOException, ImageReadException {
        GPSDTO gpsDataForImage = gpsService.getGPSDataForImage(imageDTO.getImageBase64());
        return ResponseEntity.ok(gpsDataForImage);
    }
}
