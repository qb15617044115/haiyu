package com.ruoyi.system.domain.vo;


public class CloseLiveVO {
    // 房间 id
    private Long roomId;

    private Long liveUserId;

    public Long getLiveUserId() {
        return liveUserId;
    }

    public void setLiveUserId(Long liveUserId) {
        this.liveUserId = liveUserId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
