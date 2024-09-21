package com.dev.SocialMedia.common;

import com.dev.SocialMedia.exception.CustomException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileStorageService implements StorageService {
    private final Path uploadLocation = Path.of("uploads");

    @Override
    public void init() {
        try {
            Files.createDirectory(this.uploadLocation);
        } catch (Exception e) {
            throw new CustomException("failed to create upload directory", e);
        }
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new CustomException("file is empty" + file.getOriginalFilename());
            }

            Path destinationFile = this.uploadLocation.resolve(Paths.get(file.getOriginalFilename())).normalize().toAbsolutePath();

            if (!destinationFile.getParent().equals(this.uploadLocation.toAbsolutePath())) {
                throw new CustomException("directory don't match");
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (Exception e) {
            throw new CustomException("failed to store file" + file.getOriginalFilename(), e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try (Stream<Path> files = Files.walk(this.uploadLocation, 1)
                .filter(path -> !path.equals(this.uploadLocation))
                .map(this.uploadLocation::relativize);) {
            return files;
        } catch (Exception e) {
            throw new CustomException("failed to load files");
        }
    }

    @Override
    public Path load(String filename) {
        return this.uploadLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = this.load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new CustomException("failed to read file " + filename);
            }
        } catch (Exception e) {
            throw new CustomException("could not read file " + filename, e);
        }
    }

}
