package com.tv.tutorials.coolestuffvoorcheops.services;

import java.nio.file.Path;
import java.util.stream.Stream;

//import javax.annotation.Resource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
	 void init();

	    void store(MultipartFile file);

	    Stream<Path> loadAll();

	    Path load(String filename);

	    Resource loadAsResource(String filename);

	    void deleteAll();
}
