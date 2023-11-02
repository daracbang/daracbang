package a503.daracbang.domain.bgm.service;

import a503.daracbang.domain.bgm.repository.BgmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class DeleteBgmService {

    private final BgmRepository bgmRepository;
}
