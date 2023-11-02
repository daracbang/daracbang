package a503.daracbang.domain.bgm.service;

import a503.daracbang.domain.bgm.dto.request.RegisterBgmRequest;
import a503.daracbang.domain.bgm.repository.BgmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CreateBgmService {

    private final BgmRepository bgmRepository;

    @Transactional
    public void saveBgm(RegisterBgmRequest registerBgmRequest) {
        bgmRepository.save(registerBgmRequest.toEntity());
    }
}
