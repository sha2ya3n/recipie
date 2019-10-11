package sha2ya3n.the2gen3tel4man.recepie.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

public interface ImageService {

    void saveImage(@NotNull Long recipeId, MultipartFile file);

    void getImageFromDB(Long recipeCommandId, HttpServletResponse response);
}
