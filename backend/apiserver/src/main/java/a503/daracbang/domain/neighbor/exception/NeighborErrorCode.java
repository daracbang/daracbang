package a503.daracbang.domain.neighbor.exception;

import a503.daracbang.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NeighborErrorCode implements ErrorCode {
    ALREADY_NEIGHBOR("NEIGHBOR_001","이미 이웃으로 등록된 유저입니다.",409),
    NEIGHBOR_NOT_FOUND("NEIGHBOR_002", "이웃이 아닙니다.", 404);

    private final String errorCode;
    private final String message;
    private final int statusCode;
}
