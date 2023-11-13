package a503.daracbang.domain.bgm.service;

import a503.daracbang.domain.bgm.entity.Bgm;
import a503.daracbang.domain.bgm.exception.BgmNotOwnerException;
import a503.daracbang.domain.bgm.repository.BgmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static a503.daracbang.domain.bgm.exception.BgmErrorCode.NOT_OWNER_BGM;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class DeleteBgmService {

    private final BgmRepository bgmRepository;

    private final FindBgmService findBgmService;

    @Transactional
    public void delete(Long bgmId, Long memberId) {
        Bgm bgm = findBgmService.findBgm(bgmId);
        if (!bgm.isOwner(memberId)) {
            throw new BgmNotOwnerException(NOT_OWNER_BGM);
        }
        bgmRepository.delete(bgm);
    }
}
