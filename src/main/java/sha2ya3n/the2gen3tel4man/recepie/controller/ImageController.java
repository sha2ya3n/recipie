package sha2ya3n.the2gen3tel4man.recepie.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sha2ya3n.the2gen3tel4man.recepie.services.ImageService;
import sha2ya3n.the2gen3tel4man.recepie.services.RecipieService;

@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipieService recipieService;

    public ImageController(ImageService imageService, RecipieService recipieService) {
        this.imageService = imageService;
        this.recipieService = recipieService;
    }

    @GetMapping("recipe/{recipeId}/image")
    public String handleImageGet(@PathVariable String recipeId, Model model){
        model.addAttribute("recipe", recipieService.findCommandById(Long.valueOf(recipeId)));
        return "recipe/imageuploadform";
    }

    @PostMapping("recipe/{recipeId}/image")
    public String handleImagePost(@PathVariable Long recipeId, @RequestParam MultipartFile file){
        imageService.saveImage(recipeId, file);
        return "redirect:/recipe" + recipeId + "/show";
    }
}
