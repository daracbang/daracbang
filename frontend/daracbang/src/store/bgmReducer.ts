// bgm 정보
export interface MyBgm {
  bgmId: number;
  bgmName: string;
  videoId: string;
}

export interface MusicInfoProps {
  bgm: MyBgm;
  onBgmClick: (url: string) => void;
}
