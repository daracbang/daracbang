
export class  NeighborObject {
  memberId: number;
  nickname: string;
  profileImage: string;

  constructor(memberId: number, nickname: string, profileImage: string){
    this.memberId = memberId;
    this.nickname = nickname;
    this.profileImage = profileImage;
    
  }
};
