package sha2ya3n.the2gen3tel4man.recepie.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sha2ya3n.the2gen3tel4man.recepie.commands.RecipeCommand;
import sha2ya3n.the2gen3tel4man.recepie.model.Recipie;
import sha2ya3n.the2gen3tel4man.recepie.repository.RecipieRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipieService recipieService;
    private final RecipieRepository recipieRepository;

    public ImageServiceImpl(RecipieService recipieService, RecipieRepository recipieRepository) {
        this.recipieService = recipieService;
        this.recipieRepository = recipieRepository;
    }

    // this service is to save the image that upload by controller
    @Override
    public void saveImage(Long recipeId, MultipartFile file) {
        try{
            Recipie recipie = recipieService.findById(recipeId);

            Byte[] bytes = new Byte[file.getBytes().length];

            int i = 0;
            for(byte eachByte: bytes){
                bytes[i++] = eachByte;
            }
            recipie.setImage(bytes);
            recipieRepository.save(recipie);

        } catch (IOException e) {
            log.error("IOException is happend");
            e.printStackTrace();
        }
        log.info("This is from selectImage method in image Service impl");
    }

    @Override
    public void getImageFromDB(Long recipeCommandId, HttpServletResponse response) {
        try{
            RecipeCommand recipeCommand = recipieService.findCommandById(recipeCommandId);
            byte[] bytes = new byte[recipeCommand.getImage().length];
            int i=0;
            for(byte b:bytes){
                bytes[i++] = b;
            }
            response.setContentType("/image/jpeg");
            InputStream inputStream = new ByteArrayInputStream(bytes);
            IOUtils.copy(inputStream, response.getOutputStream());

        }catch (IOException e){
            log.error("This is from getImage from DB");
            e.getStackTrace();
        }


    }
}
