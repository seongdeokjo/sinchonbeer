package com.bitcamp.sc.domain.review.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.bitcamp.sc.domain.review.domain.ReviewVO;
import com.bitcamp.sc.domain.review.repository.ReviewDao;
import com.bitcamp.sc.domain.review.service.ReviewService;
import com.bitcamp.sc.web.paging.Criteria;
import com.bitcamp.sc.web.review.dto.ReviewSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewDao reviewDao;
    @Value("${file.dir}")
    private String pathDir;

    @Override
    public List<ReviewVO> findAll(Criteria cri) {
        return reviewDao.findAll(cri);
    }

    @Override
    public ReviewVO findByIdx(long idx) {
        return reviewDao.findByIdx(idx);
    }

    @Override
    public int countReview(Criteria cri) {
        return reviewDao.getCountReview(cri);
    }

    @Override
    public void save(ReviewSaveDto saveDto) throws IOException {
        // 파일을 저장 -> 파일의 이름 추출 -> 저장
        MultipartFile file = saveDto.getFile();
        ReviewVO reviewVO = saveDto.toReview();

        if(!file.isEmpty()){
            String originalFilename = file.getOriginalFilename();
            String storeFileName = createStoreFileName(originalFilename);
            file.transferTo(new File(getFullPath(storeFileName)));
            reviewVO.setPhotoPath(storeFileName);
            log.info("review = {}",reviewVO);
        }else{
            reviewVO.setPhotoPath("basic");
        }
        reviewDao.save(reviewVO);
    }

    public String getFullPath(String storeFileName) {
        return pathDir + storeFileName;
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractedExt(originalFilename);
        log.info("ext = {}",ext); // png
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractedExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}