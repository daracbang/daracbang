
const SET_BGM_LIST = "bgm/SET_BGM_LIST" as const;

type bgmState = {
  bgmList: MyBgm[],
  memberId: number| null
}

const initalState : bgmState = {
  bgmList : [],
  memberId: null
}

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

// 한 명의 BGM 만 관리하면된다


export const setBgmListAction = (data : MyBgm[], memberId:number) => ({
  type : SET_BGM_LIST,
  payload : {bgms: data , memberId}
}) 

type bgmAction = ReturnType<typeof setBgmListAction >;
export const BGMReducer = (state:bgmState = initalState, action:bgmAction) =>{
  switch(action.type){
    case SET_BGM_LIST:
      return {
        ...state,
        bgmList : action.payload.bgms,
        memberId : action.payload.memberId
      }
    default:
      return state;
  }
}