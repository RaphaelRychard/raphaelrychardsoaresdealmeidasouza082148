package br.gov.mt.seplag.artistalbumapi.modules.album.useCases;

import br.gov.mt.seplag.artistalbumapi.modules.album.entity.Album;
import br.gov.mt.seplag.artistalbumapi.modules.album.exception.AlbumNotFoundException;
import br.gov.mt.seplag.artistalbumapi.modules.album.repository.AlbumRepository;
import br.gov.mt.seplag.artistalbumapi.modules.shared.services.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Service
public class UploadAlbumCoverUseCase {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private MinioService minioService;

    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "webp");
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB

    public Album execute(Long albumId, MultipartFile file) {
        Album album = albumRepository.findById(albumId).orElseThrow(() -> new AlbumNotFoundException(albumId));

        validateFile(file);

        if (album.getCoverImageKey() != null) {
            minioService.deleteFile(album.getCoverImageKey());
        }

        String fileKey = minioService.uploadFile(file, "albums");
        album.setCoverImageKey(fileKey);

        return albumRepository.save(album);
    }

    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Arquivo não pode estar vazio");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("Arquivo muito grande. Tamanho máximo: 5MB");
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new IllegalArgumentException("Nome do arquivo inválido");
        }

        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new IllegalArgumentException("Extensão não permitida. Permitidas: " + ALLOWED_EXTENSIONS);
        }
    }
}