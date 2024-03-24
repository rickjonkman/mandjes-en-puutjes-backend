package dev.rick.mandjesenpuutjesbackend2024.recipe;

import dev.rick.mandjesenpuutjesbackend2024.recipe.dto.RecipeImageUploadResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeImageController {

    private final RecipeImageService recipeImageService;

    @PostMapping("/auth/{recipeId}/upload-image")
    public ResponseEntity<RecipeImageUploadResponse> recipeImageUpload(@RequestParam("file") MultipartFile file, @PathVariable("recipeId") long recipeId) {

        String fileName = recipeImageService.storeFile(file);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(fileName).toUriString();
        String contentType = file.getContentType();

        return new ResponseEntity<>(new RecipeImageUploadResponse(fileName, contentType, url), HttpStatus.CREATED);
    }

    @GetMapping("/open/download-image/{fileName}")
    public ResponseEntity<Resource> downloadRecipeImage(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = recipeImageService.downloadImage(fileName);

        String mimeType;

        try {
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename()).body(resource);
    }


}
